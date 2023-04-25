package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.registerRequest

class RegisterViewModel : ViewModel() {
    suspend fun register(email:String, password:String, firstName:String, lastName:String, phone:String, street:String, city:String, zip:String):Boolean{
        return registerRequest(email, password, firstName, lastName, phone,street,city,zip)
    }
}