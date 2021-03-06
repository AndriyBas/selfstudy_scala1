package streams

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
      }
  }



  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/

    val level =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }



  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0, 0)), "0,0")
      assert(!terrain(Pos(4, 11)), "4,11")
    }
  }

  test("terrain function level 1 _ 2") {
    new Level1 {
      assert(!terrain(Pos(0, 9)), "0,9")
      assert(!terrain(Pos(5, 0)), "5,0")
      assert(!terrain(Pos(4, 3)), "4,3")
      assert(terrain(Pos(4, 8)), "4,8")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1, 1))
    }
  }

  test("neighborsWithHistory example") {
    new Level1 {
      val res = neighborsWithHistory(Block(Pos(1, 1), Pos(1, 1)), List(Left, Up))

      val correctRes = Set(
        (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
      )
      assert(res.toSet === correctRes, "neighbours are not the same")
    }
  }

  test("newNeighboursOnly ex") {
    new Level1 {
      val res = newNeighborsOnly(
        Set(
          (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
          (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
        ).toStream,

        Set(Block(Pos(1, 2), Pos(1, 3)), Block(Pos(1, 1), Pos(1, 1)))
      )

      val correctRes = Set(
        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
      )
      assert(res.toSet === correctRes, "wtf ololo")
    }
  }


//  trait SolutionChecker_2 extends GameDef with ListSolver with StringParserTerrain {
//    /**
//     * This method applies a list of moves `ls` to the block at position
//     * `startPos`. This can be used to verify if a certain list of moves
//     * is a valid solution, i.e. leads to the goal.
//     */
//    def solve(ls: List[Move]): Block =
//      ls.foldLeft(startBlock) { case (block, move) => move match {
//        case Left => block.left
//        case Right => block.right
//        case Up => block.up
//        case Down => block.down
//      }
//      }
//  }
//
//  trait Level_2 extends SolutionChecker_2 {
//    /* terrain for level 1*/
//
//    val level =
//      """ooo-------
//        |oSoooo----
//        |ooooooooo-
//        |-ooooooooo
//        |-----ooToo
//        |------ooo-""".stripMargin
//
//    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
//  }
//
//
//
//  test("new   __ terrain function level 1") {
//    new Level_2 {
//      assert(terrain(Pos(0, 0)), "0,0")
//      assert(!terrain(Pos(4, 11)), "4,11")
//    }
//  }
//
//  test("new   __ terrain function level 1 _ 2 ") {
//    new Level_2 {
//      assert(!terrain(Pos(0, 9)), "0,9")
//      assert(!terrain(Pos(5, 0)), "5,0")
//      assert(!terrain(Pos(4, 3)), "4,3")
//      assert(terrain(Pos(4, 8)), "4,8")
//    }
//  }
//
//  test("new   __ findChar level 1") {
//    new Level_2 {
//      assert(startPos == Pos(1, 1))
//    }
//  }
//
//  test("new   __ neighborsWithHistory example") {
//    new Level_2 {
//      val res = neighborsWithHistory(Block(Pos(1, 1), Pos(1, 1)), List(Left, Up))
//
//      val correctRes = Set(
//        (Block(Pos(1, 2), Pos(1, 3)), List(Right, Left, Up)),
//        (Block(Pos(2, 1), Pos(3, 1)), List(Down, Left, Up))
//      )
//      assert(res.toSet === correctRes, "neighbours are not the same")
//    }
//  }
//
//
//  test("new   __ optimal solution for level 1") {
//    new Level_2 {
//      val pFromStart = pathsFromStart
//      val sol = solution
//      assert(solve(solution) == Block(goal, goal))
//    }
//  }



  test("optimal solution for level 1") {
    new Level1 {
      val pFromStart = pathsFromStart
      val sol = solution
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
}
