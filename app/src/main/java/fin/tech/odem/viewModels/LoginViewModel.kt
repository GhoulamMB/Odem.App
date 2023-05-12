package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.loginRequest
import fin.tech.odem.utils.loginWithTokenRequest
import fin.tech.odem.utils.retrieveToken

class LoginViewModel : ViewModel(){
    suspend fun Login(email:String, password:String):Boolean{
        return loginRequest(email,password)
    }

    fun getToken(): String? {
        return retrieveToken()
    }

    suspend fun loginWithToken(token:String):Boolean{
        return loginWithTokenRequest(token)
    }
}