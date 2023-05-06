package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.acceptTransferRequest
import fin.tech.odem.utils.createTransferRequest
import fin.tech.odem.utils.declineTransferRequest

class TransferRequestViewModel : ViewModel() {

    suspend fun createRequest(reciever:String,amount:Double,reason:String){
        createTransferRequest(reciever,amount, reason)
    }
    suspend fun acceptRequest(requestId: String) {
        acceptTransferRequest(requestId)
    }

    suspend fun declineRequest(requestId: String){
        declineTransferRequest(requestId)
    }
}