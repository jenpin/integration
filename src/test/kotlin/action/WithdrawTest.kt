package action

import model.Account
import model.Bank
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WithdrawTest{

    //GIVEN the user is an existing customer
    val account: Account = Bank.customerToAccountMapping.get("user1")!!

    lateinit var testSubject: Withdraw

    @BeforeEach
    public fun before() {
        testSubject = Withdraw()
    }

    @AfterEach
    public fun after() {
        account.balance = 0.0
    }

    @Test
    fun `WHEN negative amount is withdrawn THEN an exception is thrown`(){
        val exception = Assertions.assertThrows(Exception::class.java){
            testSubject.execute(account,-1.00)
        }
        Assertions.assertEquals("Negative withdrawals are not allowed.", exception.message)
    }

    @Test
    fun `WHEN the withdrawn amount is greater than the balance THEN an exception is thrown`(){
        account.balance =20.0

        val exception = Assertions.assertThrows(Exception::class.java){
            testSubject.execute(account,30.00)
        }
        Assertions.assertEquals("Your account does not have sufficient funds.", exception.message)
    }
}