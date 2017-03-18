package impl

import bc._
import vendor.ProgramParser
import vm.VirtualMachineParser

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
class VirtualMachineParserImpl extends VirtualMachineParser with ByteCodeParser with ProgramParser {
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
  override def parse(file: String): Vector[ByteCode] = ???

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
  override def parseString(str: String): Vector[ByteCode] = ???

  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Byte`s into a correponding [[ByteCode]].
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  override def parse(bc: Vector[Byte]): Vector[ByteCode] = ???
}
