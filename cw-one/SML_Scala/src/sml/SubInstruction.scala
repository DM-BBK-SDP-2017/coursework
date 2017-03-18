package sml

/**
  * Created by dannymadell on 11/02/2017.
  */
class SubInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends Instruction(label, op) {

  // one arg constructor

  def this(array: Array[String]) {
    this(array(0), array(1), array(2).toInt, array(3).toInt, array(4).toInt)
  }


  override def execute(m: Machine) {

    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value1 - value2
  }

  override def toString(): String = {
    super.toString + " " + op1 + " + " + op2 + " to " + result + "\n"
  }
}

object SubInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new SubInstruction(label, "subtract", result, op1, op2)
}