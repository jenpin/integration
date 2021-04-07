package helper

import model.Account
import model.Bank
import model.Input
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OrchestratorTest {

    @AfterEach
    public fun after(){
        Bank.customerToAccountMapping.get("user1")?.balance = 0.0
        Bank.customerToAccountMapping.get("user2")?.balance = 0.0
    }


    @Test
    fun `WHEN no input provided THEN program exits gracefully `(){
        Orchestrator.begin("")
        Assertions.assertEquals(emptyList<Input>(),Orchestrator.models)
    }

    @Test
    fun `WHEN invalid json structure provided THEN an exception is thrown`(){
        val invalidJson = """[{"name": "user1", "instruction":"X","amount":"10.00"}"""
        Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(invalidJson)
        }
    }

    @Test
    fun `WHEN invalid json was provided THEN an exception is thrown `(){
        Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin("""[{"x": "user1"}]""")
        }
    }

    @Test
    fun `GIVEN an existing user WHEN they use an invalid instruction THEN an exception is thrown `(){
        var jsonString = """[{"name":"user1","instruction":"X","amount":"10.00"},{"name":"user1","instruction":"Test","amount":"20.202"}]""";

        Assertions.assertThrows(Exception::class.java){
            Orchestrator.begin(jsonString)
        }
    }

   @Test
    fun `GIVEN an existing user WHEN they deposit $10 THEN their account balance reflects the deposit `(){

        var jsonString = """[{"name":"user1","instruction":"DEPOSIT","amount":"10.00"},{"name":"user2","instruction":"DEPOSIT","amount":"20.202"}]""";
        Orchestrator.begin(jsonString)
        Assertions.assertEquals(2,Orchestrator.models.size)

        var expectedAccount = Account(1000)
        Assertions.assertEquals(expectedAccount,Bank.customerToAccountMapping.get("user1"))
        Assertions.assertEquals(10.00, Bank.customerToAccountMapping.get("user1")?.balance)
    }

}
