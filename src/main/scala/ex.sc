import scala.math.abs

val est = 1e-9
def isCloseEnough(x: Double, y: Double) =
  abs(abs((x - y) / y) / y) < est

def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
  def loop(guess: Double): Double = {
    println("guess : " + guess)
    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else loop(next)
  }
  loop(firstGuess)
}

def averageDump(f: Double => Double)(x: Double): Double =
  0.5 * (x + f(x))

fixedPoint(x => 1 + 2.5 * x)(1)
def sqrt(x: Double): Double = fixedPoint(averageDump(y => x / y))(1)
sqrt(4)
