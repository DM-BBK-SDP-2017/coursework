package sml

/**
  * A class for adding a value to a register
  * @param label the instruction identifier
  * @param opcode the instruction name
  * @param register the register to add the value to
  * @param value the value to add to the register
 */
case class LinInstruction(label: String, opcode: String, register: Int, value: Int) extends Instruction(label, opcode) {


  override def execute(m: Machine) =
    m.regs(register) = value

  override def toString(): String = {
    super.toString + " register " + register + " value is " + value + "\n"
  }
}

object LinInstruction {
  def apply(label: String, register: Int, value: Int) =
    new LinInstruction(label, "lin", register, value)
}
