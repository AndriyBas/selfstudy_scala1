import com.bamboo.Hello

import scala.annotation.tailrec

def abs(x: Double) = if (x >= 0) x else -x
def and(x: Boolean, y: => Boolean) = if (x) y else false
def or(x: Boolean, y: => Boolean) = if (x) true else y

def sqrt(x: Double) = {

  def isGoodEnough(guess: Double): Boolean =
    abs(guess * guess - x) / x < 1e-9

  def improve(guess: Double): Double = (guess + x / guess) * 0.5

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  sqrtIter(1)
}


def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

def fac(x: Int): Int = {

  @tailrec
  def tailFact(mul: Int, x: Int): Int =
    if (x == 1) mul else tailFact(mul * x, x - 1)

  tailFact(1, x)
}

def sum(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int =
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  loop(a, 0)
}

sum(x => x * x * x * x, 1, 4)

def sum_curry(f: Int => Int)(a: Int)(b: Int): Int = {
  def loop(a: Int): Int =
    if (a > b) 0
    else f(a) + loop(a + 1)
  loop(a)
}

def sumCubes = sum_curry(x => x * x * x) _

sumCubes(1)(2)

def wow = sum_curry(x => x * x * x) _

wow(1)(2)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, initVal: Int)
             (a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int =
    if (a > b) acc
    else loop(a + 1, combine(f(a), acc))

  loop(a, initVal)
}

def prod(a : Int)(b : Int) = mapReduce(x => x, (x, y) => x * y, 1)(a, b)

def fact(n : Int) = prod(1)(n)



fact(5)

sqrt(2)
sqrt(4)

sqrt(1e20)
sqrt(1e-20)
gcd(3, 6)

fac(10)

val h = new Hello

h.sayHello("ddd")







