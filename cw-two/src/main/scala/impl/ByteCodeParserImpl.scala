package impl

import bc.{ByteCode, ByteCodeParser, ByteCodeValues}

/**
  * Created by dannymadell on 18/03/2017.
  */
class ByteCodeParserImpl extends ByteCodeParser {
  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * You should use [[ByteCodeValues.bytecode]] to help translate
    * the individual `Byte`s into a correponding [[ByteCode]].
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
  override def parse(bc: Vector[Byte]): Vector[ByteCode] = {

    /* This is allowable because the scaladoc for bytecode specifically says that all bytes are unique

    val reverseMap = bytecode.map(_.swap)

    */

    var returnVector = Vector[ByteCode]()

    List()

    // Set counter for while loop

    var i: Int = 0

    // A while loop to increment +2 to counter when iconst is detected to create a single instruction
    // with the second value as args. increments by +1 for other values

    while (i < bc.length)  {
      if (bc(i) == bytecode("iconst")) {
        returnVector :+ (ByteCodeFactoryImpl.make(bc(i), bc(i + 1).toInt))
        i += 1
      } else {
        ByteCodeFactoryImpl.make((bc(i)))
      }
      i += 1


    }

    returnVector


}
