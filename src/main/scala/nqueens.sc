
def queens(n: Int): Set[List[Int]] = {

  def isSafeMy(col: Int, q: List[Int]) =
    if (q.contains(col))
      false
    else ((for (c <- 1 to q.length) yield (col - c, col + c)) zip q) forall {
      case ((a, b), r) => a != r && b != r
    }

  def isSafeMartin(col: Int, q: List[Int]): Boolean = {
    // aka optimization
    if (!q.isEmpty && math.abs(col - q.head) > 1)
      false
    val row = q.length
    val busyCells = (row - 1 to 0 by -1) zip q
    busyCells forall {
      case (r, c) => c != col && math.abs(c - col) != row - r
    }
  }

  def placeQueens(k: Int): Set[List[Int]] = k match {
    case 0 => Set(List())
    case _ => for {
      queens <- placeQueens(k - 1)
      col <- 0 until n
      if isSafeMartin(col, queens)
    } yield col :: queens
  }
  placeQueens(n)
}

def show(q: List[Int]) = {
  val lines = for (col <- q.reverse)
  yield Vector.fill(q.length)("* ").updated(col, "X ").mkString
  "\n" + (lines mkString "\n")
}

//queens(3)
//queens(4) map show
queens(9) take 3 map show mkString "\n"
//queens(9).size
//queens(10).size

