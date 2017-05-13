package sml

/**
  * A bnz class
  *
  * @param label the instruction identifier
  * @param opcode name of instruction
  * @param register result of op
  * @param altInst instruction to return to if register != 0
  */
case class BnzInstruction (label: String, opcode: String, register: Int, altInst: String)
  extends Instruction(label, opcode) {




  override def execute(m: Machine) = {

    if (m.regs(register) != 0) { m.pc = m.labels.labels.indexOf(altInst) }

  }



  override def toString(): String = {
    super.toString + " bnz " + register + "\n"
  }
}

object BnzInstruction {
  def apply(label: String, op1: Int, op2: String) =
    new BnzInstruction(label, "bnz", op1, op2)
}