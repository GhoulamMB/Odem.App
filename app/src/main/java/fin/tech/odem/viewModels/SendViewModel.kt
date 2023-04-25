package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.transactionRequest

class SendViewModel : ViewModel() {
    suspend fun sendMoney(amount:Double, receiver:String):Boolean{
        return transactionRequest(amount, receiver)
    }
}