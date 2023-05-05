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
import androidx.compose.material3.ButtonDefaults.textButtonColors
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.navOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.destinations.CreateTicketScreenDestination
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.TicketInformationsScreenDestination
import fin.tech.odem.utils.AppClient
import fin.tech.odem.viewModels.TicketInformationsViewModel

@Destination
@Composable
fun SupportView(navigator: DestinationsNavigator) {
    val tickets by remember { mutableStateOf(AppClient.client.tickets.sortedBy { r->r.creationDate })}

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination)}) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Support",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            LazyColumn{
                items(tickets.size){
                        i->
                    run {
                        Row(
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF303030), RoundedCornerShape(8.dp))
                                .padding(start = 8.dp, end = 8.dp)
                        ){
                            TextButton(
                                onClick = {
                                    navigator.navigate(direction = TicketInformationsScreenDestination(tickedId = tickets[i].id))
                                    },
                                colors = textButtonColors(contentColor = Color(0xFF536DFE))) {
                                Text(text = "Ticket ${i+1}")
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    }
                }
            }
        }
        Box (modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .padding(vertical = 16.dp)){
            Button(onClick = {
                             navigator.navigate(direction = CreateTicketScreenDestination)
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                Text(text = "Contact Support", fontSize = 16.sp,
                    modifier = Modifier.size(width = 124.dp, height = 24.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}