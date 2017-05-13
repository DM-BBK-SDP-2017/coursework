package sml

/**
  * A division class for machine
  *
  * @param label the instruction identifier
  * @param op the instruction name
  * @param result result of op
  * @param op1 operand 1
  * @param op2 operand 2
  */
class DivInstruction(label: String, op: String, val result: Int, val op1: Int, val op2: Int)
  extends Instruction(label, op) {


  override def execute(m: Machine) {
    val value1 = m.regs(op1)
    val value2 = m.regs(op2)
    m.regs(result) = value2 / value1
  }

  override def toString(): String = {
    super.toString + " " + op1 + " / " + op2 + " to " + result + "\n"
  }
}

object DivInstruction {
  def apply(label: String, result: Int, op1: Int, op2: Int) =
    new DivInstruction(label, "divide", result, op1, op2)
}