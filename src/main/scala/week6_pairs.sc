def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d > 0)

def p(n: Int) =
  (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j))) filter { case (x, y) => isPrime(x + y)}


p(7)

for (p <- (1 to 10)) yield p

for {
  i <- (1 until 7)
  j <- (1 until i)
  if isPrime(i + j)
} yield (i, j)


def scalP(xs: Vector[Double], ys: Vector[Double]) =
  (for {
    (x, y) <- xs zip ys
  } yield x * y).sum
Iterable

scalP(Vector(1, 3), Vector(2, 1))
