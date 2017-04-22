package implementation.ByteCodes

import bc.{ByteCode, ByteCodeValues}
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  * A bytecode to decrement the duplicate the top stack integer
  */
class Idup extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("idup")

  /**
    * Returns a new [[VirtualMachine]] with duplicate of top stack value on top of the stack
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (val1, _) = vm.pop()
    vm.push(val1)
    vm.push(val1)

  }
}
