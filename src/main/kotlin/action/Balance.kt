package action

import model.Account
import model.Bank


class Balance {

     fun execute(account: Account) {
        println("Your current balance is $${account.balance} " +
                "and the banks total balance is $${Bank.totalBalance}")
    }
}