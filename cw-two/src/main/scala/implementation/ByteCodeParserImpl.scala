package implementation

import bc.{ByteCode, ByteCodeParser, ByteCodeValues}

import scala.collection.immutable.VectorBuilder

/**
  * Created by dannymadell on 18/03/2017.
  *
  * A class to convert a vector of bytes to a vector of bytecodes
  */
class ByteCodeParserImpl extends ByteCodeParser {
  /**
    * Parses a vector of `Byte` into a vector of `ByteCode`.
    *
    * @param bc a vector of bytes representing bytecodes
    * @return a vector of `ByteCode` objects
    */
    override def parse(bc: Vector[Byte]): Vector[ByteCode] = {

      var returnVector = new VectorBuilder[ByteCode]()

      // Set counter for while loop

      var i: Int = 0

      // A while loop to increment +2 to counter when iconst is detected to create a single instruction
      // with the second value as args. increments by +1 for other values

      while (i < bc.length) {

        // detect if current element of bc is an iconst, if so, use factory to create ByteCode with this and following element

        if (bc(i) == bytecode("iconst")) {

          returnVector += (ByteCodeFactoryImpl.make(bc(i), bc(i + 1).toInt))

          // extra increment on counter to ignore the args of iconst

          i += 1

        // add any other bytecode to return vector

        } else {

          returnVector += ByteCodeFactoryImpl.make((bc(i)))
        }

        i += 1


      }

      returnVector.result()


    }


}
