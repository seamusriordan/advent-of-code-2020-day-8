import Instruction.Operation.*
import java.io.File

fun main() {
    val inputLines = File("src/main/resources/input.txt").readLines()
    val instructions = inputLines.map { Instruction.build(it) }

    val mutatedInstructions = buildInstructionMutations(instructions)

    try {
        runBootCode(instructions)
    } catch (e: AlreadyExecutedException) {
        println("[ALREADY EXECUTED] acc: ${e.acc}")
    }

    for (mutatedInstructionSet in mutatedInstructions) {
        try {
            val acc = runBootCode(mutatedInstructionSet)
            println("[SUCCESSFUL END] $acc")
        } catch (e: AlreadyExecutedException) {
        }
    }
}

private fun buildInstructionMutations(
    instructions: List<Instruction>
): List<List<Instruction>> {
    return instructions.indices.map { mutateAtIndex(instructions.toMutableList(), it) }
}

private fun mutateAtIndex(instructions: MutableList<Instruction>, mutationIndex: Int)
        : List<Instruction> {
    val instructionToMutate = instructions[mutationIndex]
    val mutatedOperation = when (instructionToMutate.operation) {
        NOP -> JMP
        JMP -> NOP
        else -> instructionToMutate.operation
    }

    instructions[mutationIndex] = Instruction(mutatedOperation, instructionToMutate.value)
    return instructions
}

fun runBootCode(instructions: List<Instruction>): Int {
    var acc = 0
    var position = 0
    val alreadyExecutedPositions = mutableListOf<Int>()

    while (
        positionIsInBounds(position, instructions.size) &&
        positionNotExecuted(position, alreadyExecutedPositions)
    ) {
        val currentInstruction = instructions[position]
        alreadyExecutedPositions.add(position)

        val nextState = processInstruction(currentInstruction, acc, position)
        position = nextState.position
        acc = nextState.acc
    }

    if (alreadyExecutedPositions.contains(position)) {
        throw AlreadyExecutedException("Already executed", acc)
    }

    return acc
}

private fun positionNotExecuted(
    position: Int,
    alreadyExecutedPositions: MutableList<Int>
) = !alreadyExecutedPositions.contains(position)

private fun positionIsInBounds(position: Int, numberOfInstructions: Int) =
    position in 0 until numberOfInstructions

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