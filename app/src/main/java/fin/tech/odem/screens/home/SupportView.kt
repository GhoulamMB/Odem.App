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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.SendViewDestination
import fin.tech.odem.screens.destinations.SupportViewDestination
import fin.tech.odem.screens.destinations.TicketInformationsScreenDestination
import fin.tech.odem.utils.AppClient

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportView(navigator: DestinationsNavigator) {
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
            LazyColumn(){
                items(AppClient.client.tickets.size){
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
                                    navigator.navigate(direction = TicketInformationsScreenDestination(AppClient.client.tickets[i]))
                                    },
                                colors = textButtonColors(contentColor = Color(0xFF536DFE))) {
                                Text(text = "Ticket ${i+1}")
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    }
                }
            }
            /*LazyColumn{
                if(AppClient.client.tickets.isEmpty()){
                    item {
                        Text(text = "No Tickets yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }else{
                    items(AppClient.client.tickets.size){
                        i->
                        run {
                            Row(
                                modifier = Modifier
                                    .height(40.dp)
                                    .fillMaxWidth()
                                    .background(Color(0xFF303030), RoundedCornerShape(8.dp))
                                    .padding(start = 8.dp, end = 8.dp)
                            ){
                                Text(text = "Ticket $i")
                                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                            }
                        }
                    }
                }
            }*/
            /*Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)){
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    placeholder = { Text("Message") },
                    label = { Text(text = "Message")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF536DFE),
                        focusedIndicatorColor = Color(0xFF536DFE),
                        cursorColor = Color(0xFF536DFE)
                    ), maxLines = 10
                )
            }*/
            //Spacer(modifier = Modifier.padding(vertical = 18.dp))
            /*Column(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {/*navigator.navigate(direction = SupportViewDestination)*/},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))
                    , modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = "Send", fontSize = 24.sp,
                        modifier = Modifier.size(width = 128.dp, height = 36.dp),
                        textAlign = TextAlign.Center)
                }
            }*/
        }
        Box (modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .padding(vertical = 16.dp)){
            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                Text(text = "Contact Support", fontSize = 16.sp,
                    modifier = Modifier.size(width = 124.dp, height = 24.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}