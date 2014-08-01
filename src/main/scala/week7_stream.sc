import forcomp.Anagrams._

//val st = (1 to 10) toStream
//
//st.tail
//
//val s2 = (6 #:: st.drop(2)).tail
//
//def from(n: Int): Stream[Int] = n #:: from(n + 1)
//
//def nats = from(0)
//
//nats take 10 toList
//
//def pow2 = from(1) map (_ * 2)
//
//pow2 take 10 toList
//
//def sieve(s : Stream[Int]) : Stream[Int] =
//  s.head #:: sieve(s.tail filter (_ % s.head != 0))
//
//val primes = sieve(from(2))
//
//primes.take(100).toList
//
//val A : Long= 24 * 60 * 60 * 1000 * 1000L
//val B : Long= 24 * 60 * 60 * 1000L
//
//val res = A./(B)
//
//val p4 = 12345 + 54321
//
//val p51 = 0x100000000L
//val p52 = 0xcafebabeL
//
//val p5 = p51 + p52
//
//(-1).toByte.toChar.toInt
//
//var x = 0x7c0
//var y = 0x7d1
//
//var a1 : Object = "as"
//var b1 : String = "we"
//
//a1 = a1 + b1
//a1 += b1
//a1
//
//val v = Map('c' -> List('c', 'c', 'c'), 'a' -> List('a', 'a', 'a'))
//
//v.map((el) => (el._1, el._2.length)).toList.sortBy(e => e._1)
//
//Anagrams.wordAnagrams("Elvis")
//
//var l: List[Int] = List[Int]()
//
//l = 1 :: l
//
//Anagrams.sentenceOccurrences(List("an", "bu"))
//
//def loop(senOcc : Occurrences) : List[Sentence] = {
//  if(senOcc.isEmpty) List(List())
//  else {
//    val allComb = combinations(senOcc)
//    (for {
//      curr <- allComb
//      wordsOptionalFromCombination = dictionaryByOccurrences.get(curr)
//      if (wordsOptionalFromCombination.isDefined)
//      restSentences = loop(subtract(senOcc, curr))
//    } yield restSe ).toList
//  }
//}

//loop(Anagrams.sentenceOccurrences(List("an", "bu")))
//List(('a', 1), ('d', 1), ('l', 1), ('r', 1)).toMap
//dictionaryByOccurrences.get(List(('a', 1)))
//val sen = List("aa", "ba", "bb")
//val t = sentenceAnagrams(sen)
val sentence = List("Linux", "rulez")

sentenceAnagrams(List("Yes", "man"))