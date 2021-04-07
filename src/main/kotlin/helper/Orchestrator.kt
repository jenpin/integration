package helper

import action.ActionType
import action.execute
import com.google.gson.GsonBuilder
import model.Account
import model.Bank
import model.Input
import java.io.File
import java.nio.file.Paths

object Orchestrator {

    var models:List<Input> = emptyList() ;

    fun begin(body: String){
        val gson = GsonBuilder().create()
        if(!body.isNullOrBlank()){
            try{
                models = gson.fromJson(body,Array<Input>::class.java).toList()
            } catch (t: Throwable) {
                throw Exception(t.message)
            }
            startProcessing()
        }

    }

    private fun startProcessing(){
            models.forEach {
                val account: Account? = Bank.customerToAccountMapping.get(it.name)
                val instruction = it.instruction
                if(account == null){
                     throw Exception("You need to register with us first.")
                }
                ActionType.valueOf(instruction).execute(account,it.amount)
        }
    }

}

fun main(args: Array<String>){

    val classloader = Thread.currentThread().contextClassLoader
    val inputStream = File(Paths.get(classloader.getResource("input.json").toURI()).toUri()).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    Orchestrator.begin(inputString)
}
