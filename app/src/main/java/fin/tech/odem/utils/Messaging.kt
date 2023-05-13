package fin.tech.odem.utils

import com.google.firebase.messaging.FirebaseMessagingService

class Messaging : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}