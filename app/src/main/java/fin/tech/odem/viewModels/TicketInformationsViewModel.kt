package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.data.models.Message
import fin.tech.odem.utils.fetchTicketMessagesRequest
import fin.tech.odem.utils.sendMessageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.Exception

class TicketInformationsViewModel : ViewModel() {
    private var _messages = MutableStateFlow(emptyList<Message>())
    var messages: StateFlow<List<Message>> = _messages.asStateFlow()

    suspend fun sendMessage(message:String,ticketId:String):Boolean{
        val result = try {
            sendMessageRequest(message,ticketId)
        }catch (e:Exception){
            false
        }
        if(result){
            messages = fetchMessages(ticketId)
        }
        return result;
    }

    suspend fun fetchMessages(ticketId: String): StateFlow<List<Message>> {
        return fetchTicketMessagesRequest(ticketId)
    }
}