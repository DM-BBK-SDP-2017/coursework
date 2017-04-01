package impl

import bc._
import ByteCodes._
import factory.VirtualMachineFactory
import impl._
import vendor._
import vm.VirtualMachineParser

import scala.collection.immutable.VectorBuilder
import scala.io.Source

/**
  * To do this you will first need to write an adapter that adapts the vendor parser and bytecode parser with a virtual
  * parser that transforms a textual bytecode program into a vector of bytecode objects. You
  * do this by implementing a vm.VirtualMachineParser. That is, you should implement
  * vm.VirtualMachineParser as the adapter — use composition of the other two parsers
  * as part of your implementation.
  *
  * After you complete the virtual machine parser you can implement a vm.VirtualMachine.
  * Your virtual machine implementation should use some internal representation for a stack
  * and provide the correct operations defined by the vm.VirtualMachine trait.
  * Lastly, modify factory.VirtualMachineFactory to return an instance of your virtual
  * machine from the virtualMachine method.
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

    //returns Vector[Instruction]

    parseHelper(VirtualMachineFactory.vendorParser.parse(file))

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

    parseHelper(VirtualMachineFactory.vendorParser.parseString(str))

  }

  //def

  def parseHelper(vectorOfInstructions: Vector[Instruction]): Vector[ByteCode] = {

    var returnVector: VectorBuilder[Byte] = new VectorBuilder[Byte]

    for (b <- vectorOfInstructions)  {

      (b.name, b.args) match {
        case ("iconst", a: Vector[Int]) => returnVector += (bytecode("iconst"), a(0).toByte)
        case (a, _) => returnVector += bytecode(a)
      }
      /*
        case ("isub", _) => new Isub
        case ("irem", _) => new Irem
        case ("ineg", _) => new Ineg
        case ("iinc", _) => new Iinc
        case ("idec", _) => new Idec
        case ("idup", _) => new Idup
        case ("iswap", _) => new Iswap
        case ("print", _) => new Print
        case ("idiv", _) => new Idiv
        case ("iadd", _) => new Iadd
        case ("imul", _) => new Imul
        case ("iconst", arg) => new Iconst(arg(0))
        case (_,_) => throw new InvalidBytecodeException(b.toString + " is not a valid bytecode!")*/

    }

    VirtualMachineFactory.byteCodeParser.parse(returnVector.result())
  }

}
