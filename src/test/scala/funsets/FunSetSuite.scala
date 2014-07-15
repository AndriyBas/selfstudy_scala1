package funsets

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
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   * - test
   * - ignore
   * - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  import funsets.FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val s12 = union(s1, s2)

    val s13 = union(s1, s3)

    val s23 = union(s2, s3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("union 13 contains 1") {
    new TestSets {
      assert(contains(s13, 1))
    }
  }

  test("intersect 13 and 23 contains only 3") {
    new TestSets {
      assert(contains(intersect(s13, s23), 3))

      assert(!contains(intersect(s13, s23), 2))
      assert(!contains(intersect(s13, s23), 1))
    }
  }

  test("map of union s12 s23 (x) => x * x") {
    new TestSets {
      val s = union(s12, s23)

      val mapS = map(s, (x: Int) => x * x)

      printSet(mapS)

      assert(contains(mapS, 1), "mapped set does not contains 1")
      assert(contains(mapS, 4), "mapped set does not contains 4")
      assert(contains(mapS, 9), "mapped set does not contains 9")

      assert(!contains(mapS, 2), "mapped set contains 2")
      assert(!contains(mapS, 3), "mapped set contains 3")
      assert(!contains(mapS, 0), "mapped set contains 0")

    }
  }

  test("exists in s12 1 and 2") {
    new TestSets {
      assert(exists(s12, (x: Int) => x == 1), "1 does not exists in s12")
    }
  }

  test("forall s12 on (x < 5)") {
    new TestSets {
      assert(forall(s12, (x: Int) => x < 5), "{1, 2} does not pass x < 5")
    }
  }

  test("forall s12 on (x < 2)") {
    new TestSets {
      //      printSet(s12)
      val p = (x: Int) => x < 2;
      assert(p(1), "not 1 < 2")
      assert(!p(2), "2 < 2")
      assert(!forall(s12, p), "{1, 2} pass x < 2")
    }
  }


}
