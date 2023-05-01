package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.data.models.Message
import fin.tech.odem.utils.fetchTicketMessagesRequest
import fin.tech.odem.utils.sendMessageRequest
import java.lang.Exception

class TicketInformationsViewModel : ViewModel() {

    suspend fun sendMessage(message:String,ticketId:String):Boolean{
        val result = try {
            sendMessageRequest(message,ticketId)
        }catch (e:Exception){
            false
        }
        return result;
    }

    suspend fun fetchMessages(ticketId: String): List<Message> {
        return fetchTicketMessagesRequest(ticketId)
    }
}