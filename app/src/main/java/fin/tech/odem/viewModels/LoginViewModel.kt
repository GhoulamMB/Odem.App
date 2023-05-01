package fin.tech.odem.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fin.tech.odem.utils.loginRequest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class LoginViewModel : ViewModel(){
    var isLoggedIn = false
    suspend fun Login(email:String, password:String):Boolean{
        try {
            isLoggedIn = loginRequest(email,password)
        }catch (_:Exception){

        }
        return isLoggedIn
    }
}