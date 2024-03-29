package fin.tech.odem

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onesignal.OneSignal
import com.ramcosta.composedestinations.DestinationsNavHost
import fin.tech.odem.screens.NavGraphs
import fin.tech.odem.ui.theme.OdemTheme


const val ONESIGNAL_APP_ID = "47f46191-c166-40ca-995b-1c1942125df2"
class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appContext: Context
        lateinit var playerId: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        playerId = OneSignal.getDeviceState()?.userId.toString()
        appContext = applicationContext
        setContent {
            OdemTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF1A1A1A))
                        .padding(top = 64.dp, bottom = 32.dp)
                ){
                    DestinationsNavHost(
                        navGraph = NavGraphs.root
                    )
                }
            }
        }
    }
}