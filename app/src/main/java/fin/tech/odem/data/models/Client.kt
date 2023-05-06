package fin.tech.odem.data.models
import kotlinx.serialization.Serializable

@Serializable
data class Client(val uid:String,val firstName:String,val lastName:String,val email:String,val phone:String,
                                 val token:String,
                                 val address:Address,
                                 val wallet:Wallet,
                                 var tickets:Array<Ticket>,
                                 var recievedRequests:Array<TransferRequest>,
                                 var sentRequests:Array<TransferRequest>)