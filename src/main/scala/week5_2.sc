def mapFunRight[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())((x: T, y: List[U]) => f(x) :: y)

def mapFunLeft[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldLeft  List[U]())((x: List[U], y: T) =>  x :+ f(y))

mapFunLeft(List(2, 6), ((x : Int) => x * x))
mapFunRight(List(2, 6), ((x : Int) => x * x))

def lengthFunRight[T](xs : List[T]) : Int =
  (xs foldRight 0)((t, i) => i + 1)

def lengthFunLeft[T](xs : List[T]) : Int =
  (xs foldLeft 0) ( (i, t) => i + 1)

lengthFunRight("1234".toList)
lengthFunLeft("1234".toList)

Nil ++ List(1, 2)
