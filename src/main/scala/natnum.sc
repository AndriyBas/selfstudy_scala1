val d = 1 :: 2 :: 3 :: 4 :: Nil


val r = 1 :: 2 :: List(5, 6) :: List()

List.apply()

// xs is already sorted
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => x :: Nil
  case y :: ys =>
    if (x <= y) x :: xs
    else y :: insert(x, ys)
}

def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case x :: ys => insert(x, isort(ys))
}

val s = isort(List(1, 5, 7, 2))

