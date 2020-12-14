import Instruction.Operation.*

fun main() {
    println("Hello World!")
}

fun runBootCode(instructions: List<Instruction>): Int {
    return instructions.filter {
        it.operation == ACC
    }.map {
        it.value
    }.sum()
}