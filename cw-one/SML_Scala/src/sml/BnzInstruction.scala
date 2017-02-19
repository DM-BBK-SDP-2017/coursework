package sml

/**
  * Created by dannymadell on 11/02/2017.
  */
class BnzInstruction (label: String, opcode: String, register: Int, altInst: String)
  extends Instruction(label, opcode) {

  // one arg constructor f5 bnz 20 f3


  def this(array: Array[String]) {
    this(array(0), array(1), array(2).toInt, array(3))
  }


  override def execute(m: Machine) = {
    for (label <- m.labels.labels) {println("label " + label)}
    //val nextInst = m.labels(m.pc + 1)
   // val regVal = m.regs(register)

    println(m.labels.labels.length)
   // if (m.regs(register) != 0) { m.pc = m.labels.labels.indexOf(altInst) }

    }



  override def toString(): String = {
    super.toString + " bnz " + register + " value isn't 0 " + "\n"
  }
}

object BnzInstruction {
  def apply(label: String, op1: Int, op2: String) =
    new BnzInstruction(label, "bnz", op1, op2)
}