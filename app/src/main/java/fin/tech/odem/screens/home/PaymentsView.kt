package fin.tech.odem.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
fun PaymentsView(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Payments",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            LazyColumn{
                if (AppClient.client.wallet.transactions.isEmpty()) {
                    item {
                        Text(text = "No transactions yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }else{
                    items(count = AppClient.client.wallet.transactions.size/*For now this should be sized to the list of transactions List*/){
                            i->
                        run {
                            Row(
                                modifier = Modifier
                                    .size(width = 380.dp, height = 40.dp)
                                    .background(Color.Transparent)
                            ) {
                                Text(text = "From someone", color = Color.White)
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                                    Text(text = "${AppClient.client.wallet.transactions[i].amount} DZD", color = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.padding(vertical = 6.dp))
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
            BottomBar(navigator, isPaymentSelected = true)
        }
    }
}
@Composable
fun HomePaymentsView() {
    Box {
        LazyColumn{
            if(AppClient.client.wallet.transactions.isEmpty()){
                item {
                    Text(text = "No transactions yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }else{
                items(AppClient.client.wallet.transactions.take(6).size){
                        i->
                    run {
                        Row(
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF303030), RoundedCornerShape(8.dp))
                                .padding(start = 8.dp, end = 8.dp)
                        ) {
                            if(AppClient.client.wallet.transactions[i].fromName == null){
                                Text(text = "${AppClient.client.wallet.transactions[i].toName}", color = Color.White)
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                                    Text(text = "+${AppClient.client.wallet.transactions[i].amount} DZD", color = Color.White)
                                }
                            }else{
                                Text(text = "${AppClient.client.wallet.transactions[i].fromName}", color = Color.White)
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                                    Text(text = "-${AppClient.client.wallet.transactions[i].amount} DZD", color = Color.White)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 6.dp))
                    }
                }
            }
        }
    }
}