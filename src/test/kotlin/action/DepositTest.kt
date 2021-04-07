package action

import model.Account
import model.Bank
import org.junit.jupiter.api.*

class DepositTest {

    //GIVEN the user is an existing customer
    val account: Account = Bank.customerToAccountMapping.get("user1")!!

    lateinit var testSubject: Deposit

    @BeforeEach
    public fun before() {
        testSubject = Deposit()
    }

    @AfterEach
    public fun after() {
        account.balance = 0.0
    }

    @Test
    fun `WHEN negative amount is deposited THEN an exception is thrown`(){
        val exception = Assertions.assertThrows(Exception::class.java){
            testSubject.execute(account,-1.00)
        }
        Assertions.assertEquals("Negative deposit amount is not allowed.", exception.message)
    }

    @Test
    fun `WHEN an amount is deposited THEN the account balance increases`(){
        testSubject.execute(account,100.00)
        Assertions.assertEquals(100.0,account.balance)
    }
}