Vector(1, 3, 5)
val s = Array(1, 3, 5)
s map (x => x)


val q = "woWowW"
q filter (c => c.isUpper)

val x1 = q zip s
val x2 = x1 unzip
val x3 = 1 :: 3 :: 4 :: -5 :: Nil
x3.flatMap(i => List(i, i * i))

x3.sum
x3.max
x3.product

1 to 10
def combinations(m: Int, n: Int) =
  (1 to m) flatMap (x => (1 to n) map (y => (x, y)))

combinations(2, 3)

def scalProd(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map { case (x, y) => x * y}.sum

scalProd(Vector(1, 2), Vector(2, 3))

def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d > 0)

isPrime(1)
isPrime(2)
isPrime(3)
isPrime(4)
isPrime(5)
isPrime(6)
isPrime(7)



