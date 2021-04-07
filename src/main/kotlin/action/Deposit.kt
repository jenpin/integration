package action

import model.Account

class Deposit  {

     fun execute(account: Account, amount: Double): Account {
          if(amount < 0){
              throw Exception("Negative deposit amount is not allowed.")
          } else {
              account.balance = account.balance + amount
          }
        return account
    }

}