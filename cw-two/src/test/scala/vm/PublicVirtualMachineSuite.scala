package vm

import factory.VirtualMachineFactory
import org.scalatest.FunSuite

class PublicVirtualMachineSuite extends FunSuite {
  val vmp = VirtualMachineFactory.virtualMachineParser
  val bcp = VirtualMachineFactory.byteCodeParser
  val vm  = VirtualMachineFactory.virtualMachine

  test("[10] a virtual machine should execute a program") {
    val bc  = vmp.parse("programs/p05.vm")
    val vm2 = vm.execute(bc)
  }

  test("[2] iconst should work correctly") {
    val bc  = vmp.parseString("iconst 1")
    val (bc2, vm2) = vm.executeOne(bc)
    assert(vm2.state.head == 1)
  }

  test("[2] iadd should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niadd")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[2] isub should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\nisub")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 1)
  }

  test("[2] iswap should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\niswap")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state(0) == 1)
    assert(next._2.state(1) == 2)
  }

  test("[2] imul should work correctly") {
    val bc  = vmp.parseString("iconst 3\niconst 5\nimul")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 3)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 15)
  }

  test("[2] idiv should work correctly") {
    val bc  = vmp.parseString("iconst 5\niconst 15\nidiv")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 15)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 3)
  }

  test("[2] idup should work correctly") {
    val bc  = vmp.parseString("iconst 1\niconst 2\nidup")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 1)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 2)
    next = next._2.executeOne(next._1)
    assert(next._2.state(0) == 2)
    assert(next._2.state(1) == 2)
    assert(next._2.state(2) == 1)

  }

  test("[2] idec should work correctly") {
    val bc = vmp.parseString("iconst 5\nidec\nidec")
    val vm2 = vm.execute(bc)
    assert(vm2.state(0) == 3)
  }

  test("[2] iinc should work correctly") {
    val bc = vmp.parseString("iconst 5\niinc\niinc")
    val vm2 = vm.execute(bc)
    assert(vm2.state(0) == 7)
  }

  test("[2] ineg should work correctly") {
    val bc = vmp.parseString("iconst 5\nineg")
    val vm2 = vm.execute(bc)
    assert(vm2.state(0) == -5)
  }

  test("[2] irem should work correctly") {
    val bc  = vmp.parseString("iconst 5\niconst 11\nirem")
    var next = vm.executeOne(bc)
    assert(next._2.state.head == 5)
    next = next._2.executeOne(next._1)
    assert(next._2.state.head == 11)
    next = next._2.executeOne(next._1)
    assert(next._2.state(0) == 1)

  }

  test("[11] execute should work correctly") {
    val bc  = vmp.parseString("iconst 9\niconst 9\niconst 9\niadd\niadd")
    val vm2 = vm.execute(bc)
    assert(vm2.state(0) == 27)
  }

  test("[12] multiple instructions should work correctly") {
    val bc  = vmp.parseString("iconst 2\niconst 3\niconst 4\nisub\niadd")
    val vm2 = vm.execute(bc)
    assert(vm2.state(0) == 3)
  }

  test("[12] multiple instructions should work correctly 2") {
      val bc  = vmp.parseString("iconst 2\niconst 3\niconst 4\nisub\niadd\nineg\nidec\nidec\niinc\nineg\niconst 8\nidiv\niconst 6\nimul\niconst 8\niswap\nisub\niconst 14\nirem\nidup\niadd")
      val vm2 = vm.execute(bc)
      assert(vm2.state(0) == 4)
      }

}
