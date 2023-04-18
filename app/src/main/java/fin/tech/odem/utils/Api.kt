package fin.tech.odem.utils

import com.google.gson.Gson
import fin.tech.odem.data.models.Client
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun LoginRequest(email:String, password:String):Boolean{
    val client = HttpClient()
    val response = client.get("http://85.215.99.211:5000/api/Login/login?email=$email&password=$password")
    val gson = Gson()
    val body = response.bodyAsText()
    val clientResponse = gson.fromJson(body, Client::class.java)
    AppClient.client = clientResponse
    if(clientResponse != null){
        return true
    }
    return false
}