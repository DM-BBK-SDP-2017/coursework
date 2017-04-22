package implementation.ByteCodes

/**
  * Created by dannymadell on 18/03/2017.
  */
import bc._
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A bytecode to print the top stack value to the console
  *
  */
class Print extends ByteCode {

  /**
    * A unique byte value representing the bytecode.
    *
    */
  override val code: Byte = bytecode("print")

  /**
    * Returns a new [[VirtualMachine]] minus top stack value which is printed to console
    *
    * @param vm the initial virtual machine
    * @return a new virtual machine
    */
  override def execute(vm: VirtualMachine): VirtualMachine = {

    val (val1, vm1) = vm.pop()
    print(val1)
    vm1

  }
}

