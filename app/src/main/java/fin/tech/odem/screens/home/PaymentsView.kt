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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.data.models.OdemTransfer
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.SupportViewDestination
import fin.tech.odem.screens.destinations.TransactionDetailsDestination
import fin.tech.odem.utils.AppClient

@Destination
@Composable
fun PaymentsView(navigator: DestinationsNavigator) {
    val transactionsState by remember {
        mutableStateOf(AppClient.client.wallet.transactions.sortedBy { t->t.date })
    }
    val clientName = "${AppClient.client.firstName} ${AppClient.client.lastName}"
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
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

            LazyColumn(modifier = Modifier.height(500.dp)){
                if (transactionsState.isEmpty()) {
                    item {
                        Text(text = "No transactions yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }else{
                    items(transactionsState.size){
                            i->
                        run {
                            Button(onClick = { navigator.navigate(direction = TransactionDetailsDestination(transactionsState[i])) },modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303030))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text(
                                        text = transactionsState[i].partyTwo,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    if(transactionsState[i].partyTwo != clientName){
                                        Text(
                                            text = "-${transactionsState[i].amount} DZD",
                                            color = Color(0xFFF44336)
                                        )
                                    }else{
                                        Text(
                                            text = "+${transactionsState[i].amount} DZD",
                                            color = Color(0xFF4CAF50)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(vertical = 6.dp))
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
            BottomBar(navigator, isPaymentSelected = true)
        }
    }
}
@Composable
fun HomePaymentsView(navigator: DestinationsNavigator) {
    val transactionState  by remember {
        mutableStateOf(AppClient.client.wallet.transactions.sortedBy { t->t.date })
    }
    val clientName = "${AppClient.client.firstName} ${AppClient.client.lastName}"
    Box {
        LazyColumn{
            if(AppClient.client.wallet.transactions.isEmpty()){
                item {
                    Text(text = "No transactions yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }else{
                items(transactionState.take(5).size){
                        i->
                    run {
                        Button(onClick = { navigator.navigate(direction = TransactionDetailsDestination(transactionState[i])) },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303030))
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.CenterVertically)
                            ) {
                                if(transactionState[i].partyOne != clientName){
                                    Text(
                                        text = AppClient.client.wallet.transactions[i].partyOne,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }else{
                                    Text(
                                        text = AppClient.client.wallet.transactions[i].partyTwo,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                if (transactionState[i].partyOne != clientName) {
                                    Text(
                                        text = "+${AppClient.client.wallet.transactions[i].amount} DZD",
                                        color = Color(0xFF4CAF50)
                                    )
                                }else{
                                    Text(
                                        text = "-${AppClient.client.wallet.transactions[i].amount} DZD",
                                        color = Color(0xFFF44336)
                                    )
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

@Composable
@Destination
fun TransactionDetails(navigator: DestinationsNavigator,transaction:OdemTransfer) {
    val clientName = "${AppClient.client.firstName} ${AppClient.client.lastName}"
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column(modifier = Modifier.fillMaxWidth()) {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            //color depends on transaction type
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF303030), RoundedCornerShape(16.dp))) {
                if(transaction.partyTwo != clientName){
                    Text(text = "-${transaction.amount} DZD",
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = Color(0xFFF44336),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Transaction details",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "When : ${transaction.date}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "From  : ${transaction.partyOne}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "To       : ${transaction.partyTwo}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Spacer(modifier = Modifier.padding(vertical = 18.dp))
                }
                else{
                    Text(text = "+${transaction.amount} DZD",
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = Color(0xFF4CAF50),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                    Text(text = "Transaction details",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "When : ${transaction.date}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "From  : ${transaction.partyOne}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Text(text = "To       : ${transaction.partyTwo}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp, top = 18.dp))
                    Spacer(modifier = Modifier.padding(vertical = 18.dp))
                }
                TextButton(onClick = { navigator.navigate(direction = SupportViewDestination) }, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "Something went wrong?", color = Color(0xFF536DFE))
                }
            }
        }
    }
}