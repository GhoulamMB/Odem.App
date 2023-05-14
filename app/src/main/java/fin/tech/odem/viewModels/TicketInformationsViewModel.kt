package fin.tech.odem.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fin.tech.odem.data.models.Message
import fin.tech.odem.utils.fetchTicketMessages
import fin.tech.odem.utils.sendMessageRequest
import kotlinx.coroutines.launch

class TicketInformationsViewModel(messagesList: List<Message>,ticketId: String) : ViewModel() {

    val Messages = mutableStateOf(messagesList)
    init {
        getTicketMessages(ticketId)
    }

    private fun getTicketMessages(ticketId: String) {
        viewModelScope.launch {
            val sorted = fetchTicketMessages(ticketId).sortedBy { m->m.timestamp }
            Messages.value = sorted
        }
    }
    suspend fun sendMessage(message:String,ticketId:String) {

        val messageResponse = sendMessageRequest(message,ticketId)
        if (messageResponse != null) {
            Messages.value = Messages.value + messageResponse
        }
    }
}


