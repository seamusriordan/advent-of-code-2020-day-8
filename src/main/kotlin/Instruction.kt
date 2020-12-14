class Instruction(
    val operation: Operation,
    val value: Int
) {
    enum class Operation {NOP, ACC, JMP}

    companion object {
        fun build(input: String): Instruction {
            val inputRegex = Regex("""(\w{3})\s([+-]\d+)""")

            val groups = inputRegex.matchEntire(input)

            val operation = when(groups?.groupValues?.get(1)) {
                "jmp" -> Operation.JMP
                "acc" -> Operation.ACC
                else -> Operation.NOP
            }

            val value = groups?.groupValues?.get(2)?.toInt()!!

            return Instruction(operation, value)
        }
    }
}