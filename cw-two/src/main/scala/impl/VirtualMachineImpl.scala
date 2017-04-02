package impl

import bc.{ByteCode, ByteCodeValues}
import factory.VirtualMachineFactory
import vm.VirtualMachine

/**
  * Created by dannymadell on 18/03/2017.
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
  override def execute(bc: Vector[ByteCode]): VirtualMachine = {

    // THIS DOESN'T WORK, SOME SORT OF FOLDING SOLUTION?

    //bc.foreach(x => executeOne(x))
    var returnVm: VirtualMachine = VirtualMachineFactory.virtualMachine
    if (bc.isEmpty) {returnVm = this}
    else if (!bc.isEmpty) {
      returnVm = executeOne(bc)._2
    }

    returnVm

  }

  /**
    * Executes the next bytecode in the vector of bytecodes.
    *
    * This method only executes a single byte code in the vector of bytecodes.
    * It returns a tuple of the new vector of bytecodes (with the executed
    * bytecode removed) and the new virtual machine.
    *
    * You may assume that `bc` contains at least 1 bytecode.
    *
    * @param bc the vector of bytecodes
    * @return a tuple of a new vector of bytecodes and virtual machine
    */
  override def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = {


    //stack.foreach(x => println("stack values -> " + x))
    //println("head " + stack.head)
    var returnVM = bc.head.execute(this)
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

    //Don't like this, not very idiomatic to scala?

    (stack.head, new VirtualMachineImpl(stack.drop(1)))

  }

    //(stack(0), VirtualMachineFactory.virtualMachine)
  /**
    * Returns the state of the virtual machine stack.
    *
    * The value at index 0 is the value on the top of the stack.
    *
    * @return the state of the stack
    */
  override def state: Vector[Int] = stack
}
