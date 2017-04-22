package implementation

import bc.ByteCodeValues
import vendor.{Instruction, InvalidInstructionFormatException, ProgramParser}

import scala.io.Source
import scala.util.matching.Regex

/**
  * A class that parses a vendor instruction list and returns a list of instructions
  */
class ProgramParserImpl extends ProgramParser with ByteCodeValues {

  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an InstructionList
    */
  override def parse(file: String): InstructionList = {

    // Get file lines into iterator

    val sourceFileLines: Iterator[String] = Source.fromFile(file).getLines()

    // Initialise Array (required for parseHelper)

    var arrayOfFileLines: Array[String] = sourceFileLines.toArray

    // pass to parseHelper, returns the InstructionList

    parseHelper(arrayOfFileLines)

  }



  /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param string the string to parse
    * @return an instruction list
    */
  override def parseString(string: String): InstructionList = {

    // Split file on new line character

    var fileLines = string.split("\n")

    // pass to parseHelper, returns the InstructionList

    parseHelper(fileLines)
  }

  /**
    * Takes output of parse or parseString, does the pattern matching and returns the InstructionList
    * @param fileLines the array of strings in the file to pass through pattern matcher
    * @return an instruction list
    */
  def parseHelper(fileLines: Array[String]): InstructionList = {

    // A regular expression to identify the iconst instruction
    // brackets around /d+ allow us to pattern match passing through the iconst digits as args

    val Pattern: Regex = """iconst\s(\d+)""".r

    // Initialise Instructionlist to collect instructions from for loop

    var instructionList: InstructionList = Vector[Instruction]()

    // Iterate over lines from file with pattern matcher

      for (line <- fileLines) line match {


          //iconst detected using regex, numericValue added to vector as integer value
          case Pattern(numericValue) => instructionList = instructionList :+  new vendor.Instruction("iconst", Vector[Int](numericValue.toInt))

            // any other acceptable arg added to instructionlist
          case acceptableArg if (names.contains(line)) => instructionList = instructionList :+ new vendor.Instruction(acceptableArg, Vector[Int]())

            // ignore empty rows
          case "" => //Skip empty row

            // anything else must be unacceptable to throw error
          case anythingElse => throw new InvalidInstructionFormatException(anythingElse + " is not recognised!")

    }

    // return instructionlist be declaring it

    instructionList
  }



}
