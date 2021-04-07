package action

import model.Bank
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class BalanceTest {

    private lateinit var testSubject: Balance

    @BeforeEach
    public fun before() {
        //GIVEN the user already has some amount in their account
        Bank.customerToAccountMapping.get("user1")?.balance = 10.101
        testSubject = Balance()

    }

    @AfterEach
    public fun after(){
        Bank.customerToAccountMapping.get("user1")?.balance = 0.0
    }

    @Test
    fun `WHEN the amount is negative THEN  the account balance remains unaffected `() {
        val orgAccount =  Bank.customerToAccountMapping.get("user1") !!
        testSubject.execute(orgAccount)

        assertEquals( 10.101, orgAccount.balance,3.0)
        assertEquals( 10.101, Bank.totalBalance,3.0)
    }

    @Test
    fun `WHEN the balance is reqeuested for THEN  the account balance is displayed`() {
        testSubject = Balance()
        val orgAccount =  Bank.customerToAccountMapping.get("user1") !!
        testSubject.execute(orgAccount)

        assertEquals( 10.101, orgAccount.balance,3.0)
        assertEquals( 10.101, Bank.totalBalance,3.0)
    }
}