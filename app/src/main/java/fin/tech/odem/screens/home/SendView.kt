package fin.tech.odem.screens.home

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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.viewModels.SendViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SendView(navigator: DestinationsNavigator) {
    val sendViewModel = SendViewModel()
    var amountValue by remember {mutableStateOf("")} //Parse to double to use it
    var receiverValue by remember {mutableStateOf("")}
    var showConfirmationAlertDialog by remember {mutableStateOf(false)}
    var showErrorAlertDialog by remember {mutableStateOf(false)}
    Box (modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Send Money",
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
                        value = amountValue,
                        onValueChange = { amountValue = it },
                        placeholder = { Text("Amount") },
                        label = { Text(text = "Amount")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                    Spacer(modifier = Modifier.padding(vertical = 24.dp))
                    TextField(
                        value = receiverValue,
                        onValueChange = { receiverValue = it },
                        placeholder = { Text("For Who?") },
                        label = { Text(text = "For Who?")},
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
                    Column(modifier = Modifier.align(alignment = CenterHorizontally)) {
                        Button(onClick = {
                                            //validate reciever email on release version
                                            if(amountValue.isNotBlank() && receiverValue.isNotBlank()){
                                                showConfirmationAlertDialog = true
                                            }else{
                                                showErrorAlertDialog = true
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
        if(showConfirmationAlertDialog){
            AlertDialog(
                containerColor = Color(0xFF2E2E2E),
                onDismissRequest = {
                    showConfirmationAlertDialog = false
                },
                title = {
                    Text(text = "Confirmation")
                },
                text = {
                    Text(text = "Are you sure you want to send $amountValue DZD to $receiverValue")
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE)),
                        onClick = {
                            sendViewModel.viewModelScope.launch {
                                if(sendViewModel.sendMoney(amountValue.toDouble(), receiverValue)){
                                    navigator.navigate(direction = HomeViewDestination)
                                }
                            }
                            showConfirmationAlertDialog = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }
        if(showErrorAlertDialog){
            AlertDialog(
                containerColor = Color(0xFF2E2E2E),
                onDismissRequest = {
                    showErrorAlertDialog = false
                },
                title = {
                    Text(text = "Error")
                },
                text = {
                    Text(text = "Invalid email or amount.")
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE)),
                        onClick = {
                            showErrorAlertDialog = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}