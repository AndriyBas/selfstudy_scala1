case class Pos(x: Int, y: Int) {
  /** The position obtained by changing the `x` coordinate by `d` */
  def dx(d: Int) = copy(x = x + d)

  /** The position obtained by changing the `y` coordinate by `d` */
  def dy(d: Int) = copy(y = y + d)
}
val vec = Vector(Vector('o', 'o'), Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'))

def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
  val x = levelVector.indexWhere(_.indexOf(c) > -1)
  val y = levelVector(x).indexOf(c)
  new Pos(x, y)
}

findChar('S', vec)

1 #:: Stream.empty


