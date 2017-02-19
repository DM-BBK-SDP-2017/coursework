package sml

import java.util

import scala.collection.mutable

//import scala.reflect.runtime.universe._
//import java.lang.reflect._
import collection.JavaConverters._


/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  private final val ADD = "add"
  private final val LIN = "lin"
  private final val BNZ = "bnz"
  private final val MUL = "mul"
  private final val SUB = "sub"
  private final val OUT = "out"
  private final val DIV = "div"

  /**
    * Method required for Scala reflection object instantiation
    */
  /*def getObject(className: String, list: List[Any]): Instruction = {
    val mirror = runtimeMirror(getClass.getClassLoader)
    val module = mirror.staticModule(className)
    mirror.reflectModule(module).instance.asInstanceOf[AddInstruction]
  }*/
  /*def getObject(str: String, fields: List[Any]): Instruction = {
    val constParams = for (f <- fields) yield f.getClass
    val objConst = java.lang.Class.forName(str).getConstructor(constParams)
    return objConst.newInstance()
    //va; Class.forName(str).newInstance(fields)
  }*/


  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")
      val javaFields: java.util.ArrayList[String] = new util.ArrayList[String]()
      fields.foreach(f => javaFields.add(f))
         // for (f <- fields) {javaFields.append(f)}
      if (fields.length > 0) {
        labels.add(fields(0))
        fields(1) match {
          case ADD =>
            //program = program :+ JavaReflectionHelper.getObject("sml.AddInstruction", javaFields)
            program = program :+ ScalaReflectionHelper.getInstruction("sml.AddInstruction", fields)
          case LIN =>
            program = program :+ LinInstruction(fields(0), fields(2).toInt, fields(3).toInt)
          case SUB =>
            program = program :+ SubInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case MUL =>
            program = program :+ MulInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case DIV =>
            program = program :+ DivInstruction(fields(0), fields(2).toInt, fields(3).toInt, fields(4).toInt)
          case OUT =>
            program = program :+ OutInstruction(fields(0), fields(2).toInt)
          case BNZ =>
            program = program :+ BnzInstruction(fields(0), fields(2).toInt, fields(3))
          case x =>
            println("Unknown instruction $x")
        } // test branch // reflection 2
      }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) = new Translator(file)
}
