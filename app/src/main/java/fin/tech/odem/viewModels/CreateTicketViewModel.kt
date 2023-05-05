package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.data.models.Ticket
import fin.tech.odem.utils.AppClient
import fin.tech.odem.utils.createTicketRequest

class CreateTicketViewModel : ViewModel() {

    suspend fun createTicket(message:String):Ticket?{
        return try {
            createTicketRequest(message,AppClient.client.uid)
        }catch (_:Exception){
            null
        }
    }
}