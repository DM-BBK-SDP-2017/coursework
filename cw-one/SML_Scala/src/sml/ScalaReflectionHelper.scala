package sml

import sml.ScalaReflectionHelper.getClass

import scala.reflect.runtime.universe._

/**
  * Created by dannymadell on 12/02/2017.
  */
class ScalaReflectionHelper {

}

object ScalaReflectionHelper {
  def getInstruction(str: String, array: Array[String]): Instruction = {


    val inst = Class.forName(str).getConstructors
    inst(0).newInstance(array).asInstanceOf[Instruction]

    /* OR
        //Class.forName(str).newInstance().asInstanceOf[Instruction]


    val actualClass = Class.forName(str)
    val foo = actualClass.newInstance().asInstanceOf[Instruction]
    */
  }
}