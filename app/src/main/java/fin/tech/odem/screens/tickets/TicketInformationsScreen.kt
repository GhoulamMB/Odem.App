package fin.tech.odem.screens.tickets

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fin.tech.odem.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.data.models.Ticket
import fin.tech.odem.screens.destinations.SupportViewDestination

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketInformationsScreen(navigator: DestinationsNavigator,ticket: Ticket) {
    var messageValue by remember { mutableStateOf("")}
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = SupportViewDestination)}) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Tickets",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            LazyColumn{
                items(ticket.messages.size){
                    i->
                    run {
                        Row(
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF303030), RoundedCornerShape(60.dp))
                                .padding(start = 8.dp, end = 8.dp)
                                , verticalAlignment = Alignment.CenterVertically
                        ){
                            var prefix = if(ticket.messages[i].isClientMessage) "You: " else "Admin: "
                            Text(text = prefix+ticket.messages[i].content,color = Color.White, modifier = Modifier.padding(start = 6.dp))
                        }
                        Spacer(modifier = Modifier.padding(vertical = 6.dp))
                    }
                }
            }
        }
        Box(modifier = Modifier
            .align(alignment = Alignment.BottomCenter)
            .padding(vertical = 16.dp)){
            Row {
                TextField(
                    modifier=Modifier.height(46.dp),
                    value = messageValue,
                    onValueChange = {messageValue = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF2E2E2E),
                        focusedIndicatorColor = Color(0xFF2E2E2E),
                        cursorColor = Color(0xFF2E2E2E)
                    ),
                    shape = RoundedCornerShape(4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                Button(onClick = { /*TODO*/ }, modifier = Modifier.height(46.dp)) {
                    Text(text = "Send")
                }
            }
        }
    }
}