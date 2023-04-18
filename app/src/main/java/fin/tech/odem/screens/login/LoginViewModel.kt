package fin.tech.odem.screens.login
import fin.tech.odem.utils.LoginRequest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel(){
    suspend fun Login(email:String, password:String):Boolean{
        var result:Boolean
        withContext(Dispatchers.IO){
            result = LoginRequest(email,password)
        }
        return result
    }
}