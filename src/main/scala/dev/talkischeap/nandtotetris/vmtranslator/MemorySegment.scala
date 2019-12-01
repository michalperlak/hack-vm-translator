package dev.talkischeap.nandtotetris.vmtranslator

sealed class MemorySegment(name: String)

case object LocalSegment extends MemorySegment("local")

case object ArgumentsSegment extends MemorySegment("arguments")

case object ThisSegment extends MemorySegment("this")

case object ThatSegment extends MemorySegment("that")

case object ConstantSegment extends MemorySegment("constant")

case object StaticSegment extends MemorySegment("static")

case object TempSegment extends MemorySegment("temp")

case object PointerSegment extends MemorySegment("pointer")