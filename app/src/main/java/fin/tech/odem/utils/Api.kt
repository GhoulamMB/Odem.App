@file:Suppress("SENSELESS_COMPARISON")

package fin.tech.odem.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fin.tech.odem.MainActivity
import fin.tech.odem.MainActivity.Companion.playerId
import fin.tech.odem.data.models.Client
import fin.tech.odem.data.models.Message
import fin.tech.odem.data.models.OdemTransfer
import fin.tech.odem.data.models.Ticket
import fin.tech.odem.data.models.TransferRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.util.InternalAPI
import java.util.Date


const val BASE_URL = "http://85.215.99.211:5000/api"
val PLAYER_ID = playerId

suspend fun loginRequest(email:String, password:String):Boolean{
    val url = "$BASE_URL/Login/login?email=$email&password=$password&oneSignalId=$PLAYER_ID"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    if(response.status.value == 200){
        val body = response.bodyAsText()
        val clientResponse = gson.fromJson(body, Client::class.java)
        AppClient.client = clientResponse
        saveToken(AppClient.client.token)
        return true
    }
    return false
}

fun saveToken(token: String) {
    MainActivity
        .appContext
        .getSharedPreferences("token", Context.MODE_PRIVATE)
        .edit()
        .putString("token", token)
        .apply()
}

fun retrieveToken():String?{
    return MainActivity
        .appContext
        .getSharedPreferences("token", Context.MODE_PRIVATE)
        .getString("token", null)
}
fun clearToken() {
    MainActivity.appContext.getSharedPreferences("token", Context.MODE_PRIVATE)
        .edit()
        .clear()
        .apply()
}
suspend fun loginWithTokenRequest(token:String):Boolean{
    val client = HttpClient()
    val response = client.get("$BASE_URL/Login/loginwithtoken?token=$token")
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    if(response.status.value == 200){
        val body = response.bodyAsText()
        val clientResponse = gson.fromJson(body, Client::class.java)
        AppClient.client = clientResponse
        return true
    }
    return false
}
@OptIn(InternalAPI::class)
suspend fun registerRequest(email:String, password:String, firstName:String, lastName:String, phone:String, street:String, city:String, zip:String):Boolean{
    val url = "$BASE_URL/Register"
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
    return response.status.value == 200
}

@OptIn(InternalAPI::class)
suspend fun transactionRequest(amount:Double, toEmail:String):Boolean{
    val url = "$BASE_URL/Transactions"
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
    return response.status.value == 200
}

suspend fun fetchTransactionsRequest(userId: String):List<OdemTransfer>{
    val url = "$BASE_URL/Transactions?userId=$userId"
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

suspend fun fetchWalletBalance(walletId: String): Double {
    val url = "$BASE_URL/Transactions/walletBalance?walletId=$walletId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = Gson()
    val body = response.bodyAsText()
    return gson.fromJson(body, Double::class.java)
}
suspend fun createTicketRequest(message:String, userId:String):Ticket{
    val url = "$BASE_URL/Support/createticket?message=$message&userId=$userId"
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
    val url = "$BASE_URL/Support/updateticket?ticketId=$ticketId&message=$message"
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
suspend fun fetchTicketsRequest(userId: String):List<Ticket>{
    val url = "$BASE_URL/Support/tickets?userId=$userId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    val body = response.bodyAsText()
    val ticketsResponse:List<Ticket> = gson.fromJson(body, object : TypeToken<List<Ticket>>() {}.type)
    if(ticketsResponse != null){
        return ticketsResponse
    }
    return emptyList()
}

suspend fun fetchTicketMessages(ticketId: String):List<Message>{
    val url = "$BASE_URL/Support/ticketMessages?ticketId=$ticketId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    if(response.status.value == 200) {
        val body = response.bodyAsText()
        return gson.fromJson(body, object : TypeToken<List<Message>>() {}.type)
    }
    return emptyList()
}

suspend fun changeEmailRequest(email:String):Boolean{
    val userId = AppClient.client.uid
    val client = HttpClient()
    val url = "$BASE_URL/Accounts?userId=$userId&email=$email&password="
    val response = client.put(url)
    if(response.status.value == 200){
        loginWithTokenRequest(AppClient.client.token)
        return true
    }
    return false
}

suspend fun changePasswordRequest(password: String):Boolean{
    val userId = AppClient.client.uid
    val client = HttpClient()
    val url = "$BASE_URL/Accounts?userId=$userId&email=&password=$password"
    val response = client.put(url)
    if(response.status.value == 200){
        loginWithTokenRequest(AppClient.client.token)
        return true
    }
    return false
}

suspend fun createTransferRequest(reciever: String,value: Double,reason:String): Boolean {
    val url = "$BASE_URL/Transactions/TransferRequest?from=${AppClient.client.email}&to=$reciever&amount=$value&reason=$reason"
    val client = HttpClient()
    val response = client.post(url)
    if(response.status.value == 200){
        loginWithTokenRequest(AppClient.client.token)
        return true
    }
    return false
}
suspend fun acceptTransferRequest(transferId:String):Boolean {
    val url = "$BASE_URL/Transactions/AcceptTransferRequest?Id=$transferId"
    val client = HttpClient()
    val response = client.post(url)
    if(response.status.value == 200){
        loginWithTokenRequest(AppClient.client.token)
        return true
    }
    return false
}

suspend fun declineTransferRequest(transferId: String):Boolean{
    val url = "$BASE_URL/Transactions/DeclineTransferRequest?Id=$transferId"
    val client = HttpClient()
    val response = client.post(url)
    if(response.status.value == 200){
        loginWithTokenRequest(AppClient.client.token)
        return true
    }
    return false
}

suspend fun getRequests(userId: String):List<TransferRequest>{
    val url = "$BASE_URL/Transactions/requests?userId=$userId"
    val client = HttpClient()
    val response = client.get(url)
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, CustomDateTypeAdapter())
        .create()
    if(response.status.value == 200) {
        val body = response.bodyAsText()
        return gson.fromJson(body, object : TypeToken<List<TransferRequest>>() {}.type)
    }
    return emptyList()
}