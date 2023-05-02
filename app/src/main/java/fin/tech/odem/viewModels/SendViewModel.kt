package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.fetchTransactions
import fin.tech.odem.utils.transactionRequest
import java.lang.Exception

class SendViewModel : ViewModel() {
    suspend fun sendMoney(amount:Double, receiver:String):Boolean{
        val result = try {
            transactionRequest(amount, receiver)
        }catch (e:Exception){
            false
        }
        if(result){
            AppClient.client.wallet.transactions = fetchTransactions(AppClient.client.uid).toTypedArray()
            AppClient.client.wallet.balance -= amount
        }
        return result
    }
}