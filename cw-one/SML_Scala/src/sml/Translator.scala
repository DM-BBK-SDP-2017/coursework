package sml

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {

  // Not sure whether spec allows us to remove these but they are now redundant with reflection

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

    // Begin for loop to add instructions to 'program' array

    for (line <- lines) {
      val fields = line.split(" ")

      if (fields.length > 0) {
        labels.add(fields(0))

        // retrieve op code and use to derive Class to instantiate

        // NOTE TO MARKER - Obviously there is an assumption here that any new instructions have the same format as the
        // ones give in the original spec e.g. instruction name (with capital first letter) + Instruction and are placed
        // into the same package

        val className = "sml." + fields(1).charAt(0).toUpper + fields(1).substring(1) + "Instruction"

        // use derived classname to get constructor that accepts a Array[String]

        val inst = Class.forName(className).getConstructor(classOf[Array[String]])

        // use this constructor to get new instance with array as argument

        program = program :+ inst.newInstance(fields).asInstanceOf[Instruction]

      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) = new Translator(file)
}