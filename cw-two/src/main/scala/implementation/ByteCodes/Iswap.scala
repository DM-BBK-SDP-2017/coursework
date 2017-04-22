package implementation.ByteCodes

import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A bytecode to swap the top two stack integers
  *
  */
class Iswap extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("iswap")

  /**
    * Returns a new [[VirtualMachine]] with top two values on stack swapped
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (val1, vm1) = vm.pop()
    val (val2, vm2) = vm1.pop()
    val vm3 = vm1.push(val1)
    vm3.push(val2)
  }
}
