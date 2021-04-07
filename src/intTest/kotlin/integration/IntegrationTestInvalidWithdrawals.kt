package integration

import helper.Orchestrator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegrationTestInvalidWithdrawals {


    @Test
    fun `GIVEN Alice is an existing customer When she withdraws -$1 THEN an exception is thrown`() {

        val input = """[
            {
                "name": "user1",
                "instruction": "WITHDRAW",
                "amount": "-1"
            },
            {
                "name": "user1",
                "instruction": "BALANCE"
            }]"""

        val exc = Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }

        Assertions.assertEquals("Negative withdrawals are not allowed.", exc.message)
    }

    @Test
    fun `When Alice does a withdrawal without any deposits THEN Your account does not have sufficient funds is shown`() {
        val input = """[
            {
                "name": "user1",
                "instruction": "WITHDRAW",
                "amount": "10.20"
            },
            {
                "name": "user1",
                "instruction": "BALANCE"
            }]"""

        val exc = Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }

        Assertions.assertEquals("Your account does not have sufficient funds.", exc.message)
    }
}