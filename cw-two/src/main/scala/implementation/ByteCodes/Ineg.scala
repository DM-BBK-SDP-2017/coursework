package implementation.ByteCodes

import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A bytecode to negate the top stack value
  */
class Ineg extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("ineg")

  /**
    * Returns a new [[VirtualMachine]] with the top stack value negated
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {
    val (val1, _) = vm.pop()

    vm.push(-val1)
  }
}
