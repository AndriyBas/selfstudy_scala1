package natnums

/**
 * Created by bamboo on 16.07.14.
 */
abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat =
    if (that == Zero) Zero
    else throw new IllegalStateException("cannot subtract from Zero")

  override def predecessor: Nat = throw new IllegalStateException("no predecessor of Zero")

  override def toString = "0"
}

class Succ(pred: Nat) extends Nat {
  override def isZero: Boolean = false

  override def +(that: Nat): Nat = new Succ(pred + that)

  override def -(that: Nat): Nat =
    if (that == Zero) this
    else pred - that.predecessor

  override def predecessor: Nat = pred

  override def toString = predecessor.toString + "."
}