package fin.tech.odem.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.acceptTransferRequest
import fin.tech.odem.utils.createTransferRequest
import fin.tech.odem.utils.declineTransferRequest
import fin.tech.odem.utils.fetchTransactionsRequest
import fin.tech.odem.utils.getRequests
import kotlinx.coroutines.launch

class TransferRequestViewModel : ViewModel() {

    var Requests = mutableStateOf(AppClient.client.recievedRequests.toList())
    init {
        fetchRequests()
    }
    private fun fetchRequests(){
        viewModelScope.launch {
            Requests.value = getRequests(AppClient.client.uid)
        }
    }
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