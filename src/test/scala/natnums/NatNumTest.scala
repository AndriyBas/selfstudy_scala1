package natnums

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 * - run the "test" command in the SBT console
 * - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class NatNumTest extends FunSuite {


  trait TestSets {
    val zero = Zero
    val one = zero.successor

    val two = new Succ(one)

    val three = two.successor

    val seven = three + three + one

  }

  test("zero") {
    new TestSets {
      assert(zero.toString == "0")
    }
  }

  test("one") {
    new TestSets {
      assert(one.toString == "0.")
    }
  }

  test("two") {
    new TestSets {
      assert(two.toString == "0..")
    }
  }

  test("three") {
    new TestSets {
      assert(three.toString == "0...")
    }
  }

  test("seven") {
    new TestSets {
      assert(seven.toString == "0.......")
    }
  }
  test("+ operation") {
    new TestSets {
      val x = three + two
      assert(x.toString == "0.....")
    }
  }

  test("predecessor operation") {
    new TestSets {
      val six = seven.predecessor
      assert(six.toString == "0......")

    }
  }

  test("- operation") {
    new TestSets {
      val five = seven - two
      assert(five.toString == "0.....")
    }
  }


}
