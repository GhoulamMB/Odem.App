package fin.tech.odem.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fin.tech.odem.data.models.Client
import fin.tech.odem.data.models.Message
import fin.tech.odem.data.models.OdemTransfer
import fin.tech.odem.data.models.Ticket
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

suspend fun loginRequest(email:String, password:String):Boolean{
    val client = HttpClient()
    val response = client.get("http://85.215.99.211:5000/api/Login/login?email=$email&password=$password")
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val clientResponse = gson.fromJson(body, Client::class.java)
    AppClient.client = clientResponse
    if(clientResponse != null){
        return true
    }
    return false
}

suspend fun loginWithTokenRequest(token:String):Boolean{
    val client = HttpClient()
    val response = client.get("http://85.215.99.211:5000/api/Login/loginwithtoken?token=$token")
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val clientResponse = gson.fromJson(body, Client::class.java)
    AppClient.client = clientResponse
    if(clientResponse != null){
        return true
    }
    return false
}
@OptIn(InternalAPI::class)
suspend fun registerRequest(email:String, password:String, firstName:String, lastName:String, phone:String, street:String, city:String, zip:String):Boolean{
    val url = "http://85.215.99.211:5000/api/Register"
    val jsonBody = """
        {
            "firstName": "$firstName",
            "lastName": "$lastName",
            "email": "$email",
            "phone": "$phone",
            "password": "$password",
            "address": {
                "street": "$street",
                "city": "$city",
                "zipCode": "$zip"
            }
        }
    """.trimIndent()
    val client = HttpClient()
    val response = client.post {
        url(url)
        body = jsonBody
        headers.append("Content-Type", "application/json")
        headers.append("Accept", "text/plain")
    }
    if(response.status.value == 200){
        return true
    }
    return false
}

@OptIn(InternalAPI::class)
suspend fun transactionRequest(amount:Double, toEmail:String):Boolean{
    val url = "http://85.215.99.211:5000/api/Transactions"
    val jsonBody = """
        {
            "amount": $amount,
            "fromEmail": "${AppClient.client.email}",
            "toEmail": "$toEmail"
        }
    """.trimIndent()
    val client = HttpClient()
    val response = client.post {
        url(url)
        body = jsonBody
        headers.append("Content-Type", "application/json")
        headers.append("Accept", "accept: */*")
    }
    if(response.status.value == 200){
        return true
    }
    return false
}

suspend fun fetchTransactions(userId: String):List<OdemTransfer>{
    val url = "http://85.215.99.211:5000/api/Transactions?userId=$userId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val transactions:List<OdemTransfer> = gson.fromJson(body, object : TypeToken<List<OdemTransfer>>() {}.type)
    if(transactions != null){
        return transactions
    }
    return emptyList()
}

suspend fun createTicketRequest(message:String, userId:String):Ticket{
    val url = "http://85.215.99.211:5000/api/Support/createticket?message=$message&userId=$userId"
    val client = HttpClient()
    val response = client.post(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val ticketResponse = gson.fromJson(body, Ticket::class.java)
    if(ticketResponse != null){
        AppClient.client.tickets = fetchTicketsRequest(userId).toTypedArray()
    }
    return ticketResponse
}

suspend fun sendMessageRequest(message: String,ticketId:String):Message?{
    val url = "http://85.215.99.211:5000/api/Support/updateticket?ticketId=$ticketId&message=$message"
    val client = HttpClient()
    val response = client.put(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    if(response.status.value == 200){
        return gson.fromJson(body,Message::class.java)
    }
    return null
}

suspend fun fetchTicketMessagesRequest(ticketId: String): Ticket? {
    val url = "http://85.215.99.211:5000/api/Support/ticket?id=$ticketId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    return gson.fromJson(body, Ticket::class.java)
}
suspend fun fetchTicketsRequest(userId: String):List<Ticket>{
    val url = "http://85.215.99.211:5000/api/Support/tickets?userId=$userId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val ticketsResponse:List<Ticket> = gson.fromJson(body, object : TypeToken<List<Ticket>>() {}.type)
    if(ticketsResponse != null){
        return ticketsResponse;
    }
    return emptyList()
}