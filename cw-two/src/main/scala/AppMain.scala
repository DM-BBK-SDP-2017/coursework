import bc.ByteCodeValues
import factory.VirtualMachineFactory

/**
  * Created by dannymadell on 01/04/2017.
  */
object AppMain extends App with ByteCodeValues {

  val vmp = VirtualMachineFactory.virtualMachineParser
  val bcp = VirtualMachineFactory.byteCodeParser
  val vm  = VirtualMachineFactory.virtualMachine
  val vp = VirtualMachineFactory.vendorParser

  val code = Vector(bytecode("iadd"))
  //val bc = bcp.parse(code)


  val insts = vp.parse("programs/p03.vm")
  println(insts.length + "LENGTH")


  val bc  = vmp.parseString("iconst 1\niconst 2\niadd")
  var next = vm.executeOne(bc)
  println(next._2.state.head == 1)
  next = next._2.executeOne(next._1)
  println(next._2.state.head)
  next = next._2.executeOne(next._1)
  println(next._2.state.head)


  //val bb = vmp.parseString()

}
