package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.transactionRequest
import java.lang.Exception

class SendViewModel : ViewModel() {
    suspend fun sendMoney(amount:Double, receiver:String):Boolean{
        val result = try {
            transactionRequest(amount, receiver)
        }catch (e:Exception){
            false
        }
        return result
    }
}