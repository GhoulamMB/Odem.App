package fin.tech.odem.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.fetchTransactionsRequest
import fin.tech.odem.utils.fetchWalletBalance
import kotlinx.coroutines.launch

class PaymentsViewModel : ViewModel() {
    var Transactions = mutableStateOf(AppClient.client.wallet.transactions.toList())
    var Balance = mutableStateOf(AppClient.client.wallet.balance)
    init {
        fetchTransactions()
        fetchBalance()
    }

    private fun fetchBalance() {
        viewModelScope.launch {
            Balance.doubleValue = fetchWalletBalance(AppClient.client.wallet.id)
        }
    }

    private fun fetchTransactions(){
        viewModelScope.launch {
            val sorted = fetchTransactionsRequest(AppClient.client.uid).toList().sortedByDescending { t->t.date }
            Transactions.value = sorted
        }
    }
}