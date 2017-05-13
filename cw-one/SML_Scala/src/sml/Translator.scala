package sml

import scala.io.Source

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 * @param fileName the path of the file being parsed
 */
class Translator(fileName: String) {


  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {

    val labels = m.labels
    var program = m.prog

    // Begin for loop to add instructions to 'program' array

    for (line <- Source.fromFile(fileName).getLines) {

        // get array of strings from the file line

      val fields: Array[String] = line.split("[\\s]+")

      // validate that array isn't empty with if statement, allows empty lines to be skipped
      if (fields.length > 0) {

        // add label to labels array
        labels.add(fields(0))

        // retrieve op code and use to derive Class to instantiate

        // NOTE TO MARKER - Obviously there is an assumption here that any new instructions have the same format as the
        // ones give in the original spec e.g. instruction name (with capital first letter) + Instruction and are placed
        // into the same package

        val className: String = "sml." + fields(1).charAt(0).toUpper + fields(1).substring(1) + "Instruction"

        // try/catch in case class isn't found

        try {

          // use derived classname to get constructor that accepts a Array[String]

          val instructionClassConstructor = Class.forName(className).getConstructors()(0)

          // loop through fields and add strings and integers (if they match the pattern) to new array

          var instanceArgs: Array[Object] = for (f <- fields) yield f match {

            case fieldString: String if fieldString.matches("\\d+") => new Integer(fieldString.toInt)
            case fieldString: String => fieldString

          }

          // use this constructor to get new instance with array as argument

          program = program :+ instructionClassConstructor.newInstance(instanceArgs: _*).asInstanceOf[Instruction]
        } catch {
          case ex: Exception => ex.printStackTrace()
        }
      }
    }

    // return Machine with labels and program populated
    new Machine(labels, program)

  }
}

object Translator {
  def apply(file: String) = new Translator(file)
}