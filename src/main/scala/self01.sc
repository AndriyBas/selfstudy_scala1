

def multiTable = {
  def mkRow(row: Int) = {
    (for (i <- 1 to 10) yield {
      // add padding to elements so that they are in one column
      String.format("%4s", (i * row).toString)
    }) mkString
  }

  val rows = for (i <- 1 to 10) yield mkRow(i)
  rows.mkString("\n")
}

def processFile(fileName: String, width: Int) = {
  //  def processLine(line: String) = {
  //    if (line.length > width) line.trim
  //    else ""
  //  }
  scala.io.Source.fromFile(fileName).getLines().filter(_.trim.length > width)
    .map(_.trim).mkString("\n")
}

processFile("/home/bamboo/GitProjects/SelfStudy2/src/main/scala/self01.sc", 40)
//val sum = (a: Int, b: Int, c: Int) => a + b + c
val sum = (_: Int) + (_: Int) + (_: Int)
val a = sum
val partSum = sum(1, _ : Int, 3)
sum(1, 2, 3)
val psum = partSum(2)

