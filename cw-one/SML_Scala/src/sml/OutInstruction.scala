package sml

/**
  * Created by dannymadell on 11/02/2017.
  */
class OutInstruction  (label: String, op: String, val value: Int)
  extends Instruction(label, op) {


  def this(array: Array[String]) {
    this(array(0), array(1), array(2).toInt)
  }

    override def execute(m: Machine) {
      println(m.regs(value))
    }

    override def toString(): String = {
      super.toString + " " + value + "\n"
    }
  }

  object OutInstruction{
    def apply(label: String, result: Int) =
      new OutInstruction(label, "out", result)
  }
