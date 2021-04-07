package model

data class Account(val id: Number) {

    var balance: Double = 0.0
    set(value){
        if(this.balance > value){
            Bank.onValueChanged(-(this.balance - value))
        } else {
            Bank.onValueChanged(value)
        }
        field = value
    }

}


