package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.acceptTransferRequest
import fin.tech.odem.utils.createTransferRequest
import fin.tech.odem.utils.declineTransferRequest
import fin.tech.odem.utils.fetchTransactionsRequest

class TransferRequestViewModel : ViewModel() {

    suspend fun createRequest(reciever:String,amount:Double,reason:String){
        createTransferRequest(reciever,amount, reason)
    }
    suspend fun acceptRequest(requestId: String,amount:Double) {
        acceptTransferRequest(requestId)
        AppClient.client.wallet.transactions = fetchTransactionsRequest(AppClient.client.uid).toTypedArray()
        AppClient.client.wallet.balance -= amount
    }

    suspend fun declineRequest(requestId: String){
        declineTransferRequest(requestId)
    }
}