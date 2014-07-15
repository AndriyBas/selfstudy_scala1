package com.bamboo

import scala.math.abs

/**
 * Created by bamboo on 12.07.14.
 */
class ex1 {

  val est = 0e-4

  def isCloseEnough(x: Double, y: Double) =
    abs(abs((x - y) / y) / y) < est

  def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
    def loop(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else loop(next)
    }
    loop(firstGuess)
  }


}
