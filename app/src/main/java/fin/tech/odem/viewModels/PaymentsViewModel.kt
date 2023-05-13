package fin.tech.odem.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.fetchTransactionsRequest
import kotlinx.coroutines.launch

class PaymentsViewModel : ViewModel() {
    var Transactions = mutableStateOf(AppClient.client.wallet.transactions.toList())
    init {
        fetchTransactions()
    }
    private fun fetchTransactions(){
        viewModelScope.launch {
            val sorted = fetchTransactionsRequest(AppClient.client.uid).toList().sortedByDescending { t->t.date }
            Transactions.value = sorted
        }
    }
}