package impl.ByteCodes

import bc.{ByteCode, ByteCodeValues}
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  */
class Idup extends ByteCode {
  /**
    * A unique byte value representing the bytecode. An implementation
    * will set this to the bytecode corresponding to the name of the
    * bytecode in [[ByteCodeValues]]
    */
  override val code: Byte = bytecode("idup")

  /**
    * Returns a new [[VirtualMachine]] after executing this bytecode operation.
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