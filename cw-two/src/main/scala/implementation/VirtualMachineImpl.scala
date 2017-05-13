package implementation

import bc.{ByteCode, ByteCodeValues}
import factory.VirtualMachineFactory
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A class for a virtual machine mimicking the Java Virtual Machine
  *
  * @param stack the virtual machine stack
  */
class VirtualMachineImpl(stack: Vector[Int]) extends VirtualMachine with ByteCodeValues {

  /**
    * Executes a vector of bytecodes.
    *
    * Note, that this is an "immutable" object. That is, it
    * will return a new virtual machine after executing each
    * of the bytecode objects in the vector.
    *
    * @param bc a vector of bytecodes
    * @return a new virtual machine
    */
  override def execute(bc: Vector[ByteCode]): VirtualMachine = bc match {

      case _ if (bc.isEmpty) => this
      case _ => {
        val (newbc, newvm) = executeOne(bc)
        newvm.execute(newbc)
      }
  }


  /**
    * Executes the next bytecode in the vector of bytecodes.
    *
    * This method only executes a single byte code in the vector of bytecodes.
    * It returns a tuple of the new vector of bytecodes (with the executed
    * bytecode removed) and the new virtual machine.
    *
    * @param bc the vector of bytecodes - must contain at least 1 bytecode
    * @return a tuple of a new vector of bytecodes and virtual machine
    */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = {

    val returnVM = bc.head.execute(this)
    (bc.drop(1), returnVM)

  }

  /**
    * Pushes an integer value onto the virtual machine stack.
    *
    * @param value the integer to push
    * @return a new virtual machine with the integer `value` pushed
    */
  override def push(value: Int): VirtualMachine = {
   new VirtualMachineImpl(value +: stack)

  }

  /**
    * Pops an integer value off of the virtual machine stack.
    *
    * @return (i, vm), where i is the integer popped and vm is the
    *         new virtual machine
    */
  override def pop(): (Int, VirtualMachine) = {

    (stack.head, new VirtualMachineImpl(stack.drop(1)))

  }

  /**
    * Returns the state of the virtual machine stack.
    *
    * The value at index 0 is the value on the top of the stack.
    *
    * @return the state of the stack
    */
  override def state: Vector[Int] = stack
}
