package impl

import bc._
import impl.ByteCodes._
import org.omg.PortableServer.IdUniquenessPolicy

/**
  * Created by dannymadell on 18/03/2017.
  */
object ByteCodeFactoryImpl extends ByteCodeFactory with ByteCodeValues {
  /**
    * Returns a [[ByteCode]].
    *
    * This method creates a new [[ByteCode]] object given the `byte`
    * that corresponds to the bytecode (see [[ByteCodeValues]]. If
    * the bytecode requires arguments then an optional integer
    * argument is provided.
    *
    * This method should throw an [[InvalidBytecodeException]] if the
    * given bytecode value is unknown.
    *
    * @param byte the byte code of a bytecode
    * @param args an optional integer argument (depends on bytecode)
    * @return a new bytecode object
    */
  override def make(byte: Byte, args: Int*): ByteCode = (byte, args) match {

      case (1, args)  => new Iconst(args(0))
      case (2, _)     => new Iadd
      case (3, _)     => new Isub
      case (4, _)     => new Imul
      case (5, _)     => new Idiv
      case (6, _)     => new Irem
      case (7, _)     => new Ineg
      case (8, _)     => new Iinc
      case (9, _)     => new Idec
      case (10, _)    => new Idup
      case (11, _)    => new Iswap
      case (12, _)    => new Print
      case (unknown,_)=> throw new InvalidBytecodeException("Unknown byte: " + unknown)

    }



}


// ap(ineg -> 7, iswap -> 11, idiv -> 5, print -> 12, iinc -> 8
// imul -> 4, iconst -> 1, idec -> 9, isub -> 3, irem -> 6, idup -> 10, iadd -> 2)
