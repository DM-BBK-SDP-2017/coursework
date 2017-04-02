package impl

import bc.ByteCodeValues
import com.sun.org.apache.bcel.internal.generic.InstructionList
import vendor.{Instruction, InvalidInstructionFormatException, ProgramParser}

import scala.io.Source

/**
  *
  */
class ProgramParserImpl extends ProgramParser with ByteCodeValues {



  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    */
  override def parse(file: String): InstructionList = {

    var instructionList: InstructionList = Vector[Instruction]()

    // Get input file and convert to Iterator

    val sourceFileLines = Source.fromFile(file).getLines()

    // Initialise Array (required for parseHelper

    var arrayOfFileLines = sourceFileLines.toArray

    // Copy input file iterator to new array

    //sourceFileLines.toArray()

    // pass to parseHelper, returns the InstructionList

    println(arrayOfFileLines.length + "FILELENGTH")
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

    // brackets around /d+ allow us to pattern match passing through the iconst digits as args
    val Pattern =
      """iconst\s(\d+)""".r

    var instructionList: InstructionList = Vector[Instruction]()

      for (line <- fileLines) {

        //println(line)


        line match {
         //        case "isub" => instructionList = instructionList :+ new vendor.Instruction("isub", Vector[Int]());
         //        case "iadd" => instructionList = instructionList :+ new vendor.Instruction("iadd", Vector[Int]());
         //        case "idiv" => instructionList = instructionList :+ new vendor.Instruction("idiv", Vector[Int]());
         //        case "imul" => instructionList = instructionList :+ new vendor.Instruction("imul", Vector[Int]());
         //        case "irem" => instructionList = instructionList :+ new vendor.Instruction("irem", Vector[Int]());
         //        case "ineg" => instructionList = instructionList :+ new vendor.Instruction("ineg", Vector[Int]());
         //        case "iinc" => instructionList = instructionList :+ new vendor.Instruction("iinc", Vector[Int]());
         //        case "idec" => instructionList = instructionList :+ new vendor.Instruction("idec", Vector[Int]());
         //        case "idup" => instructionList = instructionList :+ new vendor.Instruction("idup", Vector[Int]());
         //        case "iswap" => instructionList = instructionList :+ new vendor.Instruction("iswap", Vector[Int]());
         //        case "print" => instructionList = instructionList :+ new vendor.Instruction("print", Vector[Int]());

          case Pattern(args) => instructionList = instructionList :+  new vendor.Instruction("iconst", Vector[Int](args.toInt))
          case acceptableArg if (names.contains(line)) => instructionList = instructionList :+ new vendor.Instruction(acceptableArg, Vector[Int]())
          case anythingElse => throw new InvalidInstructionFormatException(anythingElse + " is not recognised!")

        }
    }
    //println(instructionList.size + "SIZE")
    instructionList
  }



}
