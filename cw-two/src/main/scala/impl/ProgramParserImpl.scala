package impl

import vendor.{Instruction, InvalidInstructionFormatException, ProgramParser}

import scala.io.Source

/**
  *
  */
class ProgramParserImpl extends ProgramParser {

    var instructionList: InstructionList = Vector[Instruction]()

  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    */
  override def parse(file: String): InstructionList = {

    // Get input file and convert to Iterator

    val sourceFileLines = Source.fromFile(file).getLines()

    // Initialise Array (required for parseHelper

    var arrayOfFileLines = Array[String]()

    // Copy input file iterator to new array

    sourceFileLines.copyToArray(arrayOfFileLines)

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

    //You should also be able to parse bytecode programs contained in strings (i.e., "iconst 4\nprint").

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
    var acceptableInstructions = Array(
      "iadd", "isub", "imul", "idiv", "irem", "ineg", "iinc", "idec", "idup", "iswap", "print")

    // brackets around /d+ allow us to pattern match passing through the iconst digits as args
    val Pattern =
      """iconst\s(\d+)""".r

    for (line <- fileLines) {
      line match { // not all here
        case "isub" => instructionList = instructionList :+ new vendor.Instruction("isub", Vector[Int]());
        case "irem" => instructionList = instructionList :+ new vendor.Instruction("irem", Vector[Int]());
        case "ineg" => instructionList = instructionList :+ new vendor.Instruction("ineg", Vector[Int]());
        case "iinc" => instructionList = instructionList :+ new vendor.Instruction("iinc", Vector[Int]());
        case "idec" => instructionList = instructionList :+ new vendor.Instruction("idec", Vector[Int]());
        case "idup" => instructionList = instructionList :+ new vendor.Instruction("idup", Vector[Int]());
        case "iswap" => instructionList = instructionList :+ new vendor.Instruction("iswap", Vector[Int]());
        case "print" => instructionList = instructionList :+ new vendor.Instruction("print", Vector[Int]());
        case Pattern(args) => instructionList = instructionList :+ new vendor.Instruction("iconst", Vector[Int](args.toInt));
        case line => throw new InvalidInstructionFormatException("Invalid instruction: " + line);
      }
    }

    instructionList
  }



}
