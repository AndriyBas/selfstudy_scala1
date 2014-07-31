//val romanNums = Map(1 -> "I", 2 -> "II", 3 -> "III")
//val c = Map("US" -> "Washington", "Ukraine" -> "Kiev")
//val o: Option[String] = c.get("US")
//c.get("ol")
//val f = List("apple", "pear", "orange", "pineapple")
//f groupBy (_(0))

class Poly(term0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = term0 withDefaultValue (0.0)

  def +(other: Poly) = new Poly(terms ++ (other.terms map ((t: (Int, Double)) => t._1 -> (t._2 + terms(t._1)))))

  def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + terms(exp))
  }


  def addTerm(terms : Map[Int, Double], t : (Int, Double)) = {
    val (e, c) = t
    terms + (e -> (c + terms(e)))
  }

  def +-+(other : Poly) = new Poly((other.terms foldLeft terms)(addTerm))


  override def toString =
    (for ((exp, coef) <- terms.toList.sorted.reverse) yield coef + "x^" + exp) mkString (" + ")
}

val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Poly(0 -> 3.0, 3 -> 7.0)
val p3 = p1 +-+ p2
val p4 = p1 + p2

val r = 1.0 / 0.0









