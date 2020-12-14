import Instruction.Operation.*
import java.io.File

fun main() {
    val inputLines = File("src/main/resources/input.txt").readLines()
    val instructions = inputLines.map { Instruction.build(it) }

    val acc = runBootCode(instructions)

    println(acc)
}

fun runBootCode(instructions: List<Instruction>): Int {
    var acc = 0
    var position = 0
    val alreadyExecutedPositions = mutableListOf<Int>()

    while (
        position < instructions.size &&
        !alreadyExecutedPositions.contains(position)
    ) {
        val currentInstruction = instructions[position]
        alreadyExecutedPositions.add(position)

        val nextState = processInstruction(currentInstruction, acc, position)
        position = nextState.position
        acc = nextState.acc
    }

    return acc
}

private fun processInstruction(
    currentInstruction: Instruction,
    acc: Int,
    position: Int
): BootCodeState {
    var nextAcc = acc
    var nextPosition = position
    when (currentInstruction.operation) {
        ACC -> nextAcc += currentInstruction.value
        JMP -> nextPosition += currentInstruction.value - 1
        else -> {
        }
    }
    return BootCodeState(nextPosition + 1, nextAcc)
}