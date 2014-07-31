package week5

/**
 * Created by bamboo on 18.07.14.
 */
object Main {
  def main(args : Array[String]) {
    val B\u0041\u0044 = 10
    println(B\u0041\u0044)
  }
}

object Week5 {
  def removeAt[T](n: Int, list: List[T]): List[T] = list match {
    case Nil => Nil
    case x :: xs =>
      if (n == 0) xs
      else x :: removeAt(n - 1, xs)
  }

  def removeAtNew[T](n: Int, list: List[T]): List[T] = (list take n) ::: (list drop n + 1)

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case y :: ys =>
      if (y.isInstanceOf[List[Any]]) flatten(y.asInstanceOf[List[Any]]) ::: flatten(ys)
      else y :: flatten(ys)
  }
}
