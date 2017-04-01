package sml

/**
  * Created by dannymadell on 11/02/2017.
  */
class BnzInstruction (label: String, opcode: String, register: Int, altInst: String)
  extends Instruction(label, opcode) {

  // one arg constructor f5 bnz 20 f3




  override def execute(m: Machine) = {

    if (m.regs(register) != 0) { m.pc = m.labels.labels.indexOf(altInst) }

  }



  override def toString(): String = {
    super.toString + " " + register + "\n"
  }
}

object BnzInstruction {
  def apply(label: String, op1: Int, op2: String) =
    new BnzInstruction(label, "bnz", op1, op2)
}