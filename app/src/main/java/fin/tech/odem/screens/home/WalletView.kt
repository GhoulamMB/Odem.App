package fin.tech.odem.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.utils.AppClient

@Destination
@Composable
fun WalletView(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Wallet",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            Balance()
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            WalletInformations()
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
            BottomBar(navigator, isWalletSelected = true)
        }
    }
}

@Composable
fun WalletInformations() {
    Box(modifier = Modifier
        .size(width = 380.dp, height = 220.dp)
        .background(Color(0xFF141414), RoundedCornerShape(16.dp))){
        Column(modifier = Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp)) {
            Text(text = "Account holder", color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = "${AppClient.client.firstName} ${AppClient.client.lastName}", color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Account number", color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = AppClient.client.wallet.id, color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Address", color = Color.White)
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(text = "${AppClient.client.address.street}, ${AppClient.client.address.city}, ${AppClient.client.address.zipCode}", color = Color.White)
        }
    }
}