package fin.tech.odem.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.screens.destinations.RegisterViewDestination
import fin.tech.odem.viewModels.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navigator: DestinationsNavigator) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    val loginViewModel = LoginViewModel()
    var showAlertDialog by remember {mutableStateOf(false)}
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 72.dp, bottom = 64.dp, start = 24.dp, end = 24.dp),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val customTextFieldShape = RoundedCornerShape(4.dp)

            TextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                placeholder = { Text("E-mail") },
                label = { Text(text = "E-mail")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
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
                value = passwordValue,
                onValueChange = { passwordValue = it },
                placeholder = { Text("Password") },
                label = { Text(text = "Password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    focusedLabelColor = Color(0xFF536DFE),
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            Button(onClick = {
                                    loginViewModel.viewModelScope.launch {
                                        if(emailValue.isNotBlank() && passwordValue.isNotBlank() && loginViewModel.login(emailValue, passwordValue)){
                                            emailValue = ""
                                            passwordValue=""
                                            navigator.navigate(direction = HomeViewDestination)
                                        }else{
                                            showAlertDialog = true
                                        }
                                    }
                             },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                Text(text = "Login", fontSize = 24.sp,
                    modifier = Modifier.size(width = 128.dp, height = 36.dp),
                    textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            TextButton(onClick = { navigator.navigate(direction = RegisterViewDestination) }) {
                Text(text = "Don't have an account?", color = Color(0xFF536DFE))
            }
            if(showAlertDialog){
                AlertDialog(
                    containerColor = Color(0xFF2E2E2E),
                    onDismissRequest = {
                        showAlertDialog = false
                    },
                    title = {
                        Text(text = "Login Failed")
                    },
                    text = {
                        Text(text = "Invalid email or password.")
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE)),
                            onClick = {
                                showAlertDialog = false
                            }
                        ) {
                            Text(text = "OK")
                        }
                    }
                )
            }
        }
    }
}