package sml

/**
  * A class to print a register value
  * @param label the instruction identifier
  * @param op the instruction name
  * @param value the register to print
  */
class OutInstruction  (label: String, op: String, val value: Int)
  extends Instruction(label, op) {


  override def execute(m: Machine) {
    println(m.regs(value))
  }

  override def toString(): String = {
    super.toString + " out " + value + "\n"
  }
}

object OutInstruction{
  def apply(label: String, result: Int) =
    new OutInstruction(label, "out", result)
}