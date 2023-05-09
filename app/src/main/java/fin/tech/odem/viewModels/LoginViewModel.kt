package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.loginRequest

class LoginViewModel : ViewModel(){
    suspend fun Login(email:String, password:String):Boolean{
        return loginRequest(email,password)
    }
}