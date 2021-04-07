package integration

import helper.Orchestrator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegrationTestInvalidDeposit {

    @Test
    fun `GIVEN Alice is an existing customer When she deposits -$1 THEN an exception is shown`() {

        val input = """[
            {
                "name": "user1",
                "instruction": "DEPOSIT",
                "amount": "-10.20"
            },
            {
                "name": "user1",
                "instruction": "BALANCE"
            }]"""


        val exc = Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }
        Assertions.assertEquals("Negative deposit amount is not allowed.", exc.message)
    }
}