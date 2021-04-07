package integration

import helper.Orchestrator
import model.Bank
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.System.setOut


class IntegrationTestValidScenario {
    private val standardOut = System.out
    private val standardError = System.err

    private val outputStreamCaptor = ByteArrayOutputStream()
    private val errorStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
     fun before(){
        System.setOut(PrintStream(outputStreamCaptor))
        System.setErr(PrintStream(errorStreamCaptor))
    }

    @AfterEach
    fun after() {
        setOut(standardOut)
        System.setErr(standardError)
        Bank.customerToAccountMapping.get("user1")?.balance=0.0
    }

    @Test
    fun `GIVEN Alice is an existing customer WHEN she deposits $20 and withdraws $10 THEN her balance is $10`(){
        val input = """[
            {
                "name": "user1",
                "instruction": "DEPOSIT",
                "amount": "20.20"
            },
            {
                "name": "user1",
                "instruction": "WITHDRAW",
                "amount": "10.20"
            },
            {
                "name": "user1",
                "instruction": "BALANCE"
            }]"""

        val expected = StringBuilder("Your current balance is $10.0 and the banks total balance is \$10.0").appendln()
        Orchestrator.begin(input)
        assertEquals(expected.toString(), outputStreamCaptor.toString());

    }

}