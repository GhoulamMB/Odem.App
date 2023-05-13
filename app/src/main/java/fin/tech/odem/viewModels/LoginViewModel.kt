package fin.tech.odem.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fin.tech.odem.utils.clearToken
import fin.tech.odem.utils.loginRequest
import fin.tech.odem.utils.loginWithTokenRequest
import fin.tech.odem.utils.retrieveToken
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    val AuthenticationState = mutableStateOf(false)
    init {
        validateTokenAndSetState()
    }
    private fun validateTokenAndSetState() {
        val token = getToken() // Retrieve token from your storage mechanism

        viewModelScope.launch {
            if (token != null) {
                val isValidToken = loginWithToken(token)
                AuthenticationState.value = isValidToken
            } else {
                deleteToken()
                AuthenticationState.value = false
            }
        }
    }
    suspend fun login(email:String, password:String):Boolean{
        return loginRequest(email,password)
    }

    private fun getToken(): String? {
        return retrieveToken()
    }

    private fun deleteToken() {
        clearToken()
    }

    suspend fun loginWithToken(token:String): Boolean {
        return loginWithTokenRequest(token)
    }
}