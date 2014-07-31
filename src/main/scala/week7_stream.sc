val st = (1 to 10) toStream

st.tail

val s2 = (6 #:: st.drop(2)).tail

def from(n: Int): Stream[Int] = n #:: from(n + 1)

def nats = from(0)

nats take 10 toList

def pow2 = from(1) map (_ * 2)

pow2 take 10 toList

def sieve(s : Stream[Int]) : Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2))

primes.take(100).toList

val A : Long= 24 * 60 * 60 * 1000 * 1000L
val B : Long= 24 * 60 * 60 * 1000L

val res = A./(B)

val p4 = 12345 + 54321

val p51 = 0x100000000L
val p52 = 0xcafebabeL

val p5 = p51 + p52

(-1).toByte.toChar.toInt

var x = 0x7c0
var y = 0x7d1

var a1 : Object = "as"
var b1 : String = "we"

a1 = a1 + b1
a1 += b1
a1




