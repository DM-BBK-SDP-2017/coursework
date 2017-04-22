package implementation.ByteCodes

import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A bytecode to decrement the top stack integer by one
  *
  */
class Idec extends ByteCode {
  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("idec")

  /**
    * Returns a new [[VirtualMachine]] with top stack value decremented by 1
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (val1, _) = vm.pop()
    vm.push(val1 - 1)

  }
}
