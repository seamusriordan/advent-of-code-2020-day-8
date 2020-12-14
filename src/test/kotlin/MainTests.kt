import Instruction.Operation.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTests {
    @Test
    internal fun `no instructions does nothing`() {
        val result = runBootCode(listOf())

        assertEquals(0, result)
    }

    @Test
    internal fun `one instruction nop 0 does nothing`() {

        val instructions = listOf(
            Instruction(NOP, 0)
        )
        val result = runBootCode(instructions)

        assertEquals(0, result)
    }

    @Test
    internal fun `instruction acc 0 does nothing`() {

        val instructions = listOf(
            Instruction(ACC, 0)
        )
        val result = runBootCode(instructions)

        assertEquals(0, result)
    }

    @Test
    internal fun `instruction jmp 1 does nothing`() {

        val instructions = listOf(
            Instruction(JMP, 1)
        )
        val result = runBootCode(instructions)

        assertEquals(0, result)
    }

    @Test
    internal fun `instruction acc 1 returns 1`() {

        val instructions = listOf(
            Instruction(ACC, 1)
        )
        val result = runBootCode(instructions)

        assertEquals(1, result)
    }

    @Test
    internal fun `two instructions acc 2 returns 2`() {

        val instructions = listOf(
            Instruction(ACC, 1),
            Instruction(ACC, 1)
        )
        val result = runBootCode(instructions)

        assertEquals(2, result)
    }
}