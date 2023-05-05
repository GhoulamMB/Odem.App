package fin.tech.odem.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fin.tech.odem.data.models.Message
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.loginWithTokenRequest
import fin.tech.odem.utils.sendMessageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicketInformationsViewModel(messagesList: List<Message>) : ViewModel() {

    private val _messages = MutableStateFlow(messagesList)
    val Messages: StateFlow<List<Message>> get() = _messages
    suspend fun sendMessage(message:String,ticketId:String): Message? {

        val messageResponse = sendMessageRequest(message,ticketId)
        if (messageResponse != null) {
            _messages.value = _messages.value + messageResponse
            if(loginWithTokenRequest(AppClient.client.token)){
                return messageResponse
            }
        }
        return null
    }
}


