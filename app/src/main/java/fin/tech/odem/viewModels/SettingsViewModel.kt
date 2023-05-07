package fin.tech.odem.viewModels

import androidx.lifecycle.ViewModel
import fin.tech.odem.utils.changeEmailRequest
import fin.tech.odem.utils.changePasswordRequest

class SettingsViewModel : ViewModel() {
    suspend fun changeEmail(email: String):Boolean{
        return changeEmailRequest(email)
    }

    suspend fun changePassword(password: String):Boolean{
        return changePasswordRequest(password)
    }
}