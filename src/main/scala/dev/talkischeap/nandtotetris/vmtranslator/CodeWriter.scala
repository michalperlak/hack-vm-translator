package dev.talkischeap.nandtotetris.vmtranslator

class CodeWriter {
  def writeAll(instructions: Iterable[Instruction]): List[String] = instructions.flatMap(write).toList

  def write(instruction: Instruction): List[String] = ???

  private def writeArithmetic(instruction: Instruction): List[String] = instruction match {
    case AddInstruction => binaryOp("D+A")
    case SubtractInstruction => binaryOp("A-D")
    case NegateInstruction => unaryOp("-D")
    case EqualsInstruction => comparisonOp("JEQ")
    case GreaterThanInstruction => comparisonOp("JGT")
    case LowerThanInstruction => comparisonOp("JLT")
    case AndInstruction => binaryOp("D&A")
    case OrInstruction => binaryOp("D|A")
    case NotInstruction => unaryOp("!D")
  }

  /**
   * --SP
   * D = *SP
   * --SP
   * A = *SP
   * D = op
   * *SP = D
   * ++SP
   */
  private def binaryOp(op: String): List[String] =
    decrementStackPointer() ++
      loadStackToDestination("D") ++
      decrementStackPointer() ++
      loadStackToDestination("A") :+
      cInstruction("D", Some(op)) ++
        valueToStack("D") ++
        incrementStackPointer()

  /**
   * --SP
   * D = *SP
   * D = op
   * *SP = D
   * ++SP
   */
  private def unaryOp(op: String): List[String] =
    decrementStackPointer() ++
      loadStackToDestination("D") :+
      cInstruction("D", Some(op)) ++
        valueToStack("D") ++
        incrementStackPointer()

  /**
   * --SP
   * D = *SP
   * --SP
   * A = *SP
   * D = A - D
   * D; cond to eq_label
   * *SP = 0
   * 0; JMP to ne_label
   * (eq_label)
   * *SP = -1
   * (ne_label)
   * ++SP
   */
  private def comparisonOp(cond: String): List[String] =
    decrementStackPointer() ++
      loadStackToDestination("D") ++
      decrementStackPointer() ++
      loadStackToDestination("A") :+ cInstruction("D", Some("A-D"))

  private def incrementStackPointer(): List[String] = List(
    aInstruction("SP"),
    cInstruction("M", Some("M+1"))
  )

  private def decrementStackPointer(): List[String] = List(
    aInstruction("SP"),
    cInstruction("M", Some("M-1"))
  )

  private def loadStackPointer(): List[String] = List(
    aInstruction("SP"),
    cInstruction("A", Some("M"))
  )

  private def loadStackToDestination(destination: String): List[String] =
    loadStackPointer() :+ cInstruction(destination, Some("M"))

  private def valueToStack(value: String): List[String] =
    loadStackPointer() :+ cInstruction("M", Some(value))

  private def aInstruction(address: String): String = s"@$address"

  private def cInstruction(comp: String, dest: Option[String], jump: Option[String] = None): String = {
    val builder = new StringBuilder
    dest.foreach(destValue => builder.append(destValue).append("="))
    builder.append(comp)
    jump.foreach(jumpValue => builder.append(";").append(jumpValue))
    builder.toString()
  }

  private def labelDeclaration(labelName: String): String = s"($labelName)"
}
