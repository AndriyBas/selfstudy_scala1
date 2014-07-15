class Rational(x: Int, y: Int) {

  require(y != 0, "denominator cannot be zero")

  def this(x: Int) = this(x, 1)

  private def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)

  private val g = gcd(x, y)

  val numer = x / g

  val denom = y /  g

  def +(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def -(that: Rational) = this + -that

  def unary_- : Rational = new Rational(-this.numer, this.denom)

  def <(that: Rational) = this.numer * that.denom < that.numer * this.denom

  def *(that: Rational) = new Rational(this.numer * that.numer, this.denom * that.denom)

  override
  def toString = numer + "/" + denom
}

val x = new Rational(1, 2)
val y = new Rational(1, 3)
val z = new Rational(3, 2)
x - y - z
x + z * y + y * x

x * x + y * y
//  y.add(y)
y < y

val st = new Rational(10)

funsets.FunSets.contains((x : Int) => x > 10, 20)