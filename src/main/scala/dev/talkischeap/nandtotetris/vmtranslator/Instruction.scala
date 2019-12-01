package dev.talkischeap.nandtotetris.vmtranslator

sealed trait Instruction

case class PushInstruction(segment: MemorySegment, index: Int) extends Instruction

case class PopInstruction(segment: MemorySegment, index: Int) extends Instruction

case object AddInstruction extends Instruction

case object SubtractInstruction extends Instruction

case object NegateInstruction extends Instruction

case object EqualsInstruction extends Instruction

case object GreaterThanInstruction extends Instruction

case object LowerThanInstruction extends Instruction

case object AndInstruction extends Instruction

case object OrInstruction extends Instruction

case object NotInstruction extends Instruction