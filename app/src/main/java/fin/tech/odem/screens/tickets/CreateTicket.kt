package fin.tech.odem.screens.tickets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.destinations.SupportViewDestination
import fin.tech.odem.screens.destinations.TicketInformationsScreenDestination
import fin.tech.odem.viewModels.CreateTicketViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun CreateTicketScreen(navigator: DestinationsNavigator) {
    val viewModel = CreateTicketViewModel()
    var messageValue by remember {mutableStateOf("")}
    Box (modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = SupportViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Create Ticket",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 8.dp, top = 24.dp)) {
                Column {
                    TextField(
                        value = messageValue,
                        onValueChange = { messageValue = it },
                        placeholder = { Text("Message") },
                        label = { Text(text = "Message") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            cursorColor = Color(0xFF536DFE),
                            focusedIndicatorColor = Color(0xFF536DFE),
                            focusedLabelColor = Color(0xFF536DFE),
                        )
                    )
                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    Column(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                        Button(onClick = {
                                         viewModel.viewModelScope.launch{
                                             val ticket = viewModel.createTicket(messageValue)
                                             if(ticket != null){
                                                 navigator.navigate(direction = TicketInformationsScreenDestination(tickedId = ticket.id))
                                             }
                                         }
                        },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                            Text(text = "Send", fontSize = 24.sp,
                                modifier = Modifier.size(width = 128.dp, height = 36.dp),
                                textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}