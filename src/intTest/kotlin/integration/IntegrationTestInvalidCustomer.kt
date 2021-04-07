package integration

import helper.Orchestrator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegrationTestInvalidCustomer {

    @Test
    fun `GIVEN Trent is an new customer THEN he deposits $10 THEN an exception is shown`() {

        val input = """[
            {
                "name": "user",
                "instruction": "DEPOSIT",
                "amount": "10.0"
            },
            {
                "name": "user",
                "instruction": "BALANCE"
            }]"""


        val exc = Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(input)
        }
        Assertions.assertEquals("You need to register with us first.", exc.message)
    }

}