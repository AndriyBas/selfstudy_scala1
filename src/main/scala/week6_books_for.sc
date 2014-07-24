case class Book(title: String, authors: List[String])

val books = List(
  Book("t1", List("ath_1", "a2")),
  Book("t2", List("a3", "a2")),
  Book("t3", List("ath_1", "a3")),
  Book("t4", List("a2", "a3")),
  Book("t5", List("a5"))
)

for (b <- books; a <- b.authors if a startsWith "ath")
  yield b.title

books flatMap(b =>
  b.authors.withFilter(a => a startsWith "ath") map (y => b.title))
