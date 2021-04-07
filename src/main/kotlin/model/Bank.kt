package model

interface ValueChangedLister{
    fun onValueChanged(amount: Double)
}

object Bank: ValueChangedLister{

    val customerToAccountMapping: Map<String, Account>
    var totalBalance: Double = 0.0

    init{
        customerToAccountMapping = mapOf(
            "user1" to Account(1000 ),
            "user2" to Account(2000),
            "user3" to Account(3000)
        )
    }

    override fun onValueChanged(amount: Double) {
        totalBalance = totalBalance + amount
    }
}