package fin.tech.odem.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fin.tech.odem.data.models.Client
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.util.InternalAPI
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