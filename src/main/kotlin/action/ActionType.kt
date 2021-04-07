package action

import model.Account

enum class ActionType {
    WITHDRAW,
    DEPOSIT,
    BALANCE
}

fun ActionType.execute(account:Account, amount: Double){
    when(this) {
        ActionType.WITHDRAW -> Withdraw().execute(account, amount)
        ActionType.DEPOSIT -> Deposit().execute(account, amount)
        ActionType.BALANCE -> Balance().execute(account)
    }
}
