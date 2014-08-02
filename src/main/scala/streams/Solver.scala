package streams

/**
 * This component implements the solver for the Bloxorz game
 */
trait Solver extends GameDef {

  /**
   * Returns `true` if the block `b` is at the final position
   */
  def done(b: Block): Boolean = {
    val curr = b.b1
    (b.isStanding && (curr.x == goal.x) && (curr.y == goal.y))
  }

  /**
   * This function takes two arguments: the current block `b` and
   * a list of moves `history` that was required to reach the
   * position of `b`.
   *
   * The `head` element of the `history` list is the latest move
   * that was executed, i.e. the last move that was performed for
   * the block to end up at position `b`.
   *
   * The function returns a stream of pairs: the first element of
   * the each pair is a neighboring block, and the second element
   * is the augmented history of moves required to reach this block.
   *
   * It should only return valid neighbors, i.e. block positions
   * that are inside the terrain.
   */
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {

    //    @Deprecated
    //    def blockToStreamEl(block: (Block, Move)): (Block, List[Move]) =
    //      (block._1, block._2 :: history)

    // apply foldLeft to all legal neighbours of current block
    b.legalNeighbors.foldLeft[Stream[(Block, List[Move])]](Stream.empty) { case (stream, (block, move)) => (block, move :: history) #:: stream}
    // and yield correct new element to the stream
  }


  /**
   * This function returns the list of neighbors without the block
   * positions that have already been explored. We will use it to
   * make sure that we don't explore circular paths.
   */
  def newNeighborsOnly(neighbors: Stream[(Block, List[Move])],
                       explored: Set[Block]): Stream[(Block, List[Move])] = {
    @Deprecated // used for testing
    def loop(stream: Stream[(Block, List[Move])], acc: Stream[(Block, List[Move])]): Stream[(Block, List[Move])] =
      if (stream.isEmpty) acc
      else {
        val (b, path) = stream.head
        if (explored.contains(b)) loop(stream.tail, acc)
        else loop(stream.tail, stream.head #:: acc)
      }
    //    loop(neighbors, Stream.empty)

    neighbors.filter(state => !explored.contains(state._1))
  }


  /**
   * The function `from` returns the stream of all possible paths
   * that can be followed, starting at the `head` of the `initial`
   * stream.
   *
   * The blocks in the stream `initial` are sorted by ascending path
   * length: the block positions with the shortest paths (length of
   * move list) are at the head of the stream.
   *
   * The parameter `explored` is a set of block positions that have
   * been visited before, on the path to any of the blocks in the
   * stream `initial`. When search reaches a block that has already
   * been explored before, that position should not be included a
   * second time to avoid cycles.
   *
   * The resulting stream should be sorted by ascending path length,
   * i.e. the block positions that can be reached with the fewest
   * amount of moves should appear first in the stream.
   *
   * Note: the solution should not look at or compare the lengths
   * of different paths - the implementation should naturally
   * construct the correctly sorted stream.
   */
  def from(initial: Stream[(Block, List[Move])],
           explored: Set[Block]): Stream[(Block, List[Move])] = {

    @Deprecated
    def updateExploredSet(newStream: Stream[(Block, List[Move])], explored: Set[Block]): Set[Block] = {
      newStream.foldLeft[Set[Block]](explored)((set, el) => set + el._1)
    }

    @Deprecated
    def loop(initial: Stream[(Block, List[Move])],
             explored: Set[Block],
             acc: Stream[(Block, List[Move])]): Stream[(Block, List[Move])] =
      if (initial.isEmpty) acc
      else {
        val (block, path) = initial.head
        val neigh = neighborsWithHistory(block, path)
        val newNeigh = newNeighborsOnly(neigh, explored)
        val newSet = updateExploredSet(newNeigh, explored)
        loop(initial.tail, newSet, acc ++ newNeigh)
      }


    //    loop(initial, explored, Stream.empty)
    @Deprecated
    def iter(el: (Block, List[Move])) = {
      val (block, path) = el
      newNeighborsOnly(neighborsWithHistory(block, path), explored)
    }
    // TODO : check if this elements of the stream are sorted by length of path : List[Move]
    //    initial.foldLeft[Stream[(Block, List[Move])]](Stream.empty)((stream, el) => stream ++ iter(el))
    val (block, path) = initial.head
    newNeighborsOnly(neighborsWithHistory(block, path), explored)
  }

  /**
   * The stream of all paths that begin at the starting block.
   */
  lazy val pathsFromStart: Stream[(Block, List[Move])] = {

    def loop(curr: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] =
      if (curr.isEmpty) curr
      else {
        val newStreamFromHead = from(curr, explored)
        val newSet = updateExploredSet(newStreamFromHead, explored)
        curr.head #:: loop(curr.tail ++ newStreamFromHead, newSet)
      }

    def updateExploredSet(newStream: Stream[(Block, List[Move])], explored: Set[Block]): Set[Block] = {
      newStream.foldLeft[Set[Block]](explored)((set, el) => set + el._1)
    }

    @Deprecated
    def iter(curr: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] = {
      val newStream = from(curr, explored)
      val newSet = updateExploredSet(newStream, explored)
      if (newSet == explored) curr
      else iter(newStream, newSet)
    }

    //    iter((startBlock, List()) #:: Stream.empty, Set(startBlock))
    loop((startBlock, List()) #:: Stream.empty, Set(startBlock))
  }

  /**
   * Returns a stream of all possible pairs of the goal block along
   * with the history how it was reached.
   */
  lazy val pathsToGoal: Stream[(Block, List[Move])] = {
    @Deprecated // used for testing
    def loop(stream: Stream[(Block, List[Move])], acc: Stream[(Block, List[Move])]): Stream[(Block, List[Move])] =
      if (stream.isEmpty) acc
      else {
        val (b, path) = stream.head
        if (done(b)) loop(stream.tail, stream.head #:: acc)
        else loop(stream.tail, acc)
      }
    //    loop(pathsFromStart, Stream.empty)

    pathsFromStart.filter((el) => done(el._1))
  }

  /**
   * The (or one of the) shortest sequence(s) of moves to reach the
   * goal. If the goal cannot be reached, the empty list is returned.
   *
   * Note: the `head` element of the returned list should represent
   * the first move that the player should perform from the starting
   * position.
   */
  lazy val solution: List[Move] =
    if (pathsToGoal.isEmpty) List()
    else pathsToGoal.head._2
}
