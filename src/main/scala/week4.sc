trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false

  override def toString = "[" + head + tail + "]"
}

class Nil[T] extends List[T] {
  def isEmpty = true

  def head = throw new NoSuchElementException("Nil.head")

  def tail = throw new NoSuchElementException("Nil.head")

  override def toString = "."
}

def nth[T >: Int <: Any](n: Int, list: List[T]): T =
  if (list.isEmpty) throw new IndexOutOfBoundsException("empty list")
  else if (n == 0) list.head
  else nth(n - 1, list.tail)

def singleton[T](elem: T) = new Cons(elem, new Nil[T])

val a1 = new Cons(1, new Cons(2, new Cons(3, new Nil)))

object List {
  def apply[T](x : T, y : T) : List[T] = new Cons(x, new Cons(y, new Nil))
  true
}

def arr1 = List(1, 2)
nth(2, a1)


singleton(1)
singleton(true)

