package implementation.ByteCodes

import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  * A bytecode to add an integer value to the VM stack
  *
  * @param value the integer value to add to stack
  */
class Iconst(val value: Int) extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("iconst")

  /**
    * Returns a new [[VirtualMachine]] after pushing value to stack
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {

    vm.push(value)

  }
}
