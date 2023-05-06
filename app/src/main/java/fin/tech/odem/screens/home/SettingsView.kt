package fin.tech.odem.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.ChangeEmailScreenDestination
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.SettingsViewDestination
import fin.tech.odem.utils.AppClient
import fin.tech.odem.viewModels.SettingsViewModel
import kotlinx.coroutines.launch

@Destination
@Composable
fun SettingsView(navigator: DestinationsNavigator) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = HomeViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Settings",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Text(text = "Security", fontSize = 14.sp, color = Color.White, modifier = Modifier.padding(start = 12.dp, top = 16.dp, end = 0.dp, bottom = 8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Button(onClick = { navigator.navigate(direction = ChangeEmailScreenDestination) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(text = "E-mail")
                        Text(text = AppClient.client.email)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = ">", color = Color.White, fontSize = 20.sp)
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Button(onClick = { navigator.navigate(direction = ChangeEmailScreenDestination) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Text(text = "Password")
                        Text(text = "********")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = ">", color = Color.White, fontSize = 20.sp)
                }
            }
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
            BottomBar(navigator)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ChangeEmailScreen(navigator: DestinationsNavigator) {
    val viewModel = SettingsViewModel()
    var emailValue by remember {
        mutableStateOf(AppClient.client.email)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = SettingsViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "E-mail Settings",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Column {
                Text(text = "Account email", color = Color.White, fontSize = 14.sp)
                TextField(
                    value = emailValue,
                    onValueChange = { emailValue = it },
                    placeholder = { Text("E-mail") },
                    label = { Text(text = "E-mail")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF536DFE),
                        focusedIndicatorColor = Color(0xFF536DFE),
                        cursorColor = Color(0xFF536DFE)
                    )
                )
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                                     viewModel.viewModelScope.launch {
                                         viewModel.changeEmail(emailValue)
                                         emailValue = AppClient.client.email
                                     }
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                        Text(text = "Save", fontSize = 24.sp,
                            modifier = Modifier.size(width = 128.dp, height = 36.dp),
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ChangePasswordScreen(navigator: DestinationsNavigator) {
    val viewModel = SettingsViewModel()
    var passwordValue by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp)){
        Column {
            Row {
                IconButton(onClick = { navigator.navigate(direction = SettingsViewDestination) }) {
                    Image(modifier = Modifier.size(48.dp),painter = painterResource(id = R.drawable.back), contentDescription ="back")
                }
                Text(text = "Password Settings",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Column {
                Text(text = "Account password", color = Color.White, fontSize = 14.sp)
                TextField(
                    value = passwordValue,
                    onValueChange = { passwordValue = it },
                    placeholder = { Text("password") },
                    label = { Text(text = "password")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF536DFE),
                        focusedIndicatorColor = Color(0xFF536DFE),
                        cursorColor = Color(0xFF536DFE)
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                                     viewModel.viewModelScope.launch {
                                         viewModel.changePassword(passwordValue)
                                         passwordValue = ""
                                     }
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                        Text(text = "Save", fontSize = 24.sp,
                            modifier = Modifier.size(width = 128.dp, height = 36.dp),
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}