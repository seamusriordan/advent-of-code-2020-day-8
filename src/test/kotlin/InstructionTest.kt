import Instruction.Operation.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InstructionTest {
    @Test
    internal fun `noop input builds noop instruction`() {
        val instruction = Instruction.build("nop +0")

        assertEquals(NOP, instruction.operation)
        assertEquals(0, instruction.value)
    }

    @Test
    internal fun `acc 1 input builds acc 1 instruction`() {
        val instruction = Instruction.build("acc +1")

        assertEquals(ACC, instruction.operation)
        assertEquals(1, instruction.value)
    }

    @Test
    internal fun `acc -1 input builds acc -1 instruction`() {
        val instruction = Instruction.build("acc -1")

        assertEquals(ACC, instruction.operation)
        assertEquals(-1, instruction.value)
    }

    @Test
    internal fun `acc -1 input builds jmp 0 instruction`() {
        val instruction = Instruction.build("jmp +0")

        assertEquals(JMP, instruction.operation)
        assertEquals(0, instruction.value)
    }
}