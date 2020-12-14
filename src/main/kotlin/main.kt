import Instruction.Operation.*
import java.io.File

fun main() {
    val inputLines = File("src/main/resources/input.txt").readLines()
    val instructions = inputLines.map{Instruction.build(it)}

    val acc = runBootCode(instructions)

    println(acc)
}

fun runBootCode(instructions: List<Instruction>): Int {
    var acc = 0
    var position = 0
    val alreadyExecutedPositions = mutableListOf<Int>()

    while(position < instructions.size){
        val currentInstruction = instructions[position]
        if(alreadyExecutedPositions.contains(position)) {
            break
        }
        alreadyExecutedPositions.add(position)

        when(currentInstruction.operation) {
            ACC -> acc += currentInstruction.value
            JMP -> position += currentInstruction.value - 1
            else -> {}
        }
        position += 1
    }

    return acc
}