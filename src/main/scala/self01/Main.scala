package self01

/**
 * Created by bamboo on 31.07.14.
 */
object Main {

  def getLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def main(args: Array[String]) = {
    val rootFile = new java.io.File("./src/main/scala")
    val files =
      for {
        f <- rootFile.listFiles()
        if (f.getName.endsWith(".sc"))
        line <- getLines(f)
        trimmedLine = line.trim
        if (trimmedLine.startsWith("val"))
      } yield {
        trimmedLine.take(2) ++ trimmedLine.drop(2)
      }

    print(files.mkString("\n"))
  }

}
