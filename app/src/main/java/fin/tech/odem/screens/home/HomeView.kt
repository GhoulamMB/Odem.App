package fin.tech.odem.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.SettingsViewDestination
import fin.tech.odem.screens.destinations.SupportViewDestination
import fin.tech.odem.utils.AppClient


@Destination
@Composable
fun HomeView(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Welcome(navigator)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Balance()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            HomePaymentsView(navigator)
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomEnd).padding(top = 12.dp)) {
            BottomBar(navigator, isHomeSelected = true)
        }
    }
}

@Composable
fun Welcome(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.person),
                contentDescription = "Person logo", modifier = Modifier.size(width = 40.dp, height = 40.dp))
            Column {
                Text(text = "Welcome Back", color = Color.White)
                Text(text = AppClient.client.firstName, color = Color.White)
            }
            SettingsSupportButton(navigator)
        }
    }
}

@Composable
fun SettingsSupportButton(navigator: DestinationsNavigator) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterEnd)
        ){
            Row {
                IconButton(onClick = { navigator.navigate(direction = SupportViewDestination) }) {
                    Image(painter = painterResource(id = R.drawable.support), contentDescription ="support" )
                }
                IconButton(onClick = { navigator.navigate(direction = SettingsViewDestination) }) {
                    Image(painter = painterResource(id = R.drawable.settings), contentDescription ="settings" )
                }
            }
        }
    }
}

@Composable
fun Balance() {
    var balance = rememberUpdatedState(AppClient.client.wallet.balance)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color(0xFF141414), RoundedCornerShape(16.dp))){
        Column(modifier = Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp)) {
            Text(text = "Your balance is", color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            Row {
                Text(text = "${balance.value} DZD", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}