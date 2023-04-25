package fin.tech.odem.viewModels
import fin.tech.odem.utils.loginRequest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel(){
    suspend fun Login(email:String, password:String):Boolean{
        var result:Boolean
        withContext(Dispatchers.IO){
            result = loginRequest(email,password)
        }
        return result
    }
}