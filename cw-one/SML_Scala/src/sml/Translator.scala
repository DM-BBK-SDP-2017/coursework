package sml

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  private final val ADD = "add"
  private final val LIN = "lin"
  private final val BNZ = "bnz"
  private final val MUL = "mul"
  private final val SUB = "sub"
  private final val OUT = "out"
  private final val DIV = "div"

  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")

      if (fields.length > 0) {
        val className = "sml." + fields(1).charAt(0).toUpper + fields(1).substring(1) + "Instruction"
        println(className)
        val inst = Class.forName(className).getConstructor(classOf[Array[String]])
        //println("consts length " + inst.length)
        //println("fields length " + fields.length)
        //println(inst.newInstance(fields).asInstanceOf[Instruction])
        //program = program :+ new OutInstruction(Array[String]("f5", "out", "20"))
        //for (field <- fields) {println(field)}
        program = program :+ inst.newInstance(fields).asInstanceOf[Instruction]
        //println(" program length " + program.length)

        /*fields(1) match {
          case ADD =>
            //program = program :+ JavaReflectionHelper.getObject("sml.AddInstruction", javaFields)
            program = program :+ ScalaReflectionHelper.getInstruction("sml.AddInstruction", fields)
          case LIN =>
            program = program :+ LinInstruction(fields(0), fields(2).toInt, fields(3).toInt)
          case SUB =>
            program = program :+ SubInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case MUL =>
            program = program :+ MulInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case DIV =>
            program = program :+ DivInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case OUT =>
            program = program :+ OutInstruction(fields(0), fields(2).toInt)
          case BNZ =>
            program = program :+ BnzInstruction(fields(0), fields(2).toInt, fields(3))
          case x =>
            println("Unknown instruction $x")
        } // test branch // reflection 2*/
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) = new Translator(file)
}
