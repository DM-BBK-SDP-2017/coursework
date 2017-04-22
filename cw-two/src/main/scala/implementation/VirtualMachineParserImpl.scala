package implementation

import bc._
import ByteCodes._
import factory.VirtualMachineFactory
import implementation._
import vendor.{InvalidInstructionFormatException, _}
import vm.VirtualMachineParser

import scala.collection.immutable.VectorBuilder
import scala.io.Source

/**
  *
  *
  *
  *
  *
  *
  *
  *
  *
  *
  *
  */
class VirtualMachineParserImpl extends VirtualMachineParser with ByteCodeValues {


  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a file into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program file correctly.
    *
    * @param file the file containing a program
    * @return a vector of bytecodes
    */
  override def parse(file: String): Vector[ByteCode] = {

    // try / catch catches InvalidINstructionFormatException and rethrows as InvalidBytecodeException

    try {parseHelper(VirtualMachineFactory.vendorParser.parse(file))}
    catch {
      case ex: InvalidInstructionFormatException => throw new InvalidBytecodeException(ex.getMessage)
    }

  //converter.parse(returnVector)
}

  /**
    * Returns a vector of [[bc.ByteCode]].
    *
    * This method parses a string into a vector of bytecode objects.
    * Note, this method should throw a [[bc.InvalidBytecodeException]]
    * if it fails to parse a program string correctly.
    *
    * @param str a string containing a program
    * @return a vector of bytecodes
    */
  override def parseString(str: String): Vector[ByteCode] = {
    try {parseHelper(VirtualMachineFactory.vendorParser.parseString(str))}
    catch {
      case ex: InvalidInstructionFormatException => throw new InvalidBytecodeException(ex.getMessage)
    }

  }


  def parseHelper(vectorOfInstructions: Vector[Instruction]): Vector[ByteCode] = {

    var returnVector: VectorBuilder[Byte] = new VectorBuilder[Byte]

    for (b <- vectorOfInstructions)  {

      (b.name, b.args) match {
        case ("iconst", instructionArgs: Vector[Int]) => returnVector = returnVector += (bytecode("iconst"), instructionArgs(0).toByte)
        case (instructionName: String, _) if names.contains(instructionName) => returnVector = returnVector += bytecode(instructionName)
        case (instructionName: String, _ ) => throw new InvalidBytecodeException(s"Value $instructionName is not supported!")
      }
    }

    VirtualMachineFactory.byteCodeParser.parse(returnVector.result())

  }

}
