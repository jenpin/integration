package integration

import helper.Orchestrator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class IntegrationTestInvalidInstruction {

    @Test
    fun `GIVEN Alice is an existing customer WHEN an invalid command is executed an exception is thrown`(){
        val input = """[
            {
                "name": "user1",
                "instruction": "hi",
                "amount": "20.20"
            }]"""

        Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }
    }

    @Test
    fun `GIVEN Alice is an existing customer WHEN no instruction is executed an exception is thrown`(){
        val input = """[
            {
                "name": "user1",
                "amount": "20.20"
            }]"""

        Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }
    }
}