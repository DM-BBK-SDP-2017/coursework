package implementation.ByteCodes

import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A byte code to return the modules of the top two stack integers
  */
class Irem extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("irem")

  /**
    * Returns a new [[VirtualMachine]] minus two values that are popped and the modulus of pushed back onto stack
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (val1, vm1) = vm.pop()
    val (val2, vm2) = vm1.pop()
    vm2.push(val1 % val2)
  }
}
