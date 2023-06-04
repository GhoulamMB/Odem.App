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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.data.models.TransferRequest
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.RequestViewDestination
import fin.tech.odem.screens.destinations.TransferRequestViewDestination
import fin.tech.odem.viewModels.TransferRequestViewModel
import kotlinx.coroutines.launch

@Destination
@Composable
fun RequestView(navigator: DestinationsNavigator) {
    val viewModel = viewModel<TransferRequestViewModel>()
    var amountValue by remember { mutableStateOf("") } //Parse to double to use it
    var receiverValue by remember { mutableStateOf("") }
    var reasonValue by remember { mutableStateOf("") }
    val requests by viewModel.Requests

    Box (modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Request Money",
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
                        label = { Text(text = "Amount") },
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
                        placeholder = { Text("From Who?") },
                        label = { Text(text = "From Who?") },
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
                    Spacer(modifier = Modifier.padding(vertical = 24.dp))
                    TextField(
                        value = reasonValue,
                        onValueChange = { reasonValue = it },
                        placeholder = { Text("Reason") },
                        label = { Text(text = "Reason") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
                                             //validate reciever email on release version
                                             if(viewModel.createRequest(receiverValue,amountValue.toDouble(),reasonValue)){
                                                 navigator.navigate(direction = HomeViewDestination)
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
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "Recieved Requests", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
            LazyColumn{
                if(requests.isEmpty()){
                    item {
                        Text(text = "No requests yet", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    }
                }else{
                    items(requests.size){
                            i->
                        run {
                            if(!requests[i].checked){
                                Button(onClick = { navigator.navigate(direction = TransferRequestViewDestination(requests[i])) },
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(start = 8.dp, end = 8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303030))
                                    ) {
                                    Row(modifier = Modifier
                                        .height(40.dp)
                                        .fillMaxWidth()
                                        .background(Color(0xFF303030), RoundedCornerShape(8.dp))
                                        .padding(start = 8.dp, end = 8.dp)) {
                                        Column {
                                            Text(text = requests[i].from)
                                            Text(text = "${requests[i].amount} DZD")
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
    }
}

@Destination
@Composable
fun TransferRequestView(navigator: DestinationsNavigator,transferRequest: TransferRequest) {
    val viewModel = viewModel<TransferRequestViewModel>()
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
                Text(text = "Request Details",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row {
                    Text(text = "From:         ${transferRequest.from}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Amount:    ${transferRequest.amount}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.padding(vertical = 16.dp)) {
                    Text(text = "Reason:     ${transferRequest.reason}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                                     showConfirmationAlertDialog = true
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                        Text(text = "Accept", fontSize = 16.sp,
                            modifier = Modifier.size(width = 64.dp, height = 18.dp),
                            textAlign = TextAlign.Center)
                    }
                    Button(onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.declineRequest(transferRequest.id)
                            navigator.navigate(direction = RequestViewDestination)
                        }
                                     },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                        Text(text = "Reject", fontSize = 16.sp,
                            modifier = Modifier.size(width = 64.dp, height = 18.dp),
                            textAlign = TextAlign.Center)
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
                    Text(text = "Are you sure you want to send ${transferRequest.amount} DZD to ${transferRequest.to}")
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE)),
                        onClick = {
                            viewModel.viewModelScope.launch {
                                if(viewModel.acceptRequest(transferRequest.id)){
                                    navigator.navigate(direction = HomeViewDestination)
                                }else{
                                    showErrorAlertDialog = true
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