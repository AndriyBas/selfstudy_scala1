import week5.Week5


Week5.removeAt(1, "123456".toList)
Week5.removeAtNew(1, "123456".toList)



Week5.flatten(List(List(1, 1), 2, List(3, List(5, 8))))

def mergeSort[T](list: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = list.length / 2
  if (n == 0) list
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (xs, Nil) => xs
      case (Nil, ys) => ys
      case (x :: xs1, y :: ys1) =>
        if (ord.lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (l, r) = list splitAt n
    merge(mergeSort(l), mergeSort(r))
  }
}

val a = List(-2, -7, -8, 1, 4, 7, 2, 1, 9, 10, 12, 4, 2, 7)
val t = mergeSort(a)(Ordering.Int)

a map (x => x * x)

a filter (x => x < 0)
a partition (x => x < 0)

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, last) = xs span (y => y == x)
    first :: pack(last)
}

val b = "aaaabbbbaacc".toList
pack(b)

def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (ys => (ys.head, ys.length))

encode(b)

def sum(xs: List[Int]): Int = (xs foldLeft 0)(_ + _)

def product(xs: List[Int]): Int = (1 :: xs) reduceLeft (_ * _)

sum(Nil)
