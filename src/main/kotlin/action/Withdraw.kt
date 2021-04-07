package action

import model.Account

class Withdraw  {

     fun execute(account: Account, toWithdraw: Double): Account {
        val currentBalance = account.balance
        if(toWithdraw < 0){
            throw Exception("Negative withdrawals are not allowed.")
        } else if(toWithdraw > currentBalance) {
            throw Exception("Your account does not have sufficient funds.")
        }

        account.balance = currentBalance - toWithdraw

        return account
    }
}