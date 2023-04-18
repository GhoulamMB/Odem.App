package fin.tech.odem.screens.login

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.screens.destinations.HomeViewDestination

val registerViewModel = RegisterViewModel()
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(navigator: DestinationsNavigator) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 72.dp, bottom = 64.dp, start = 24.dp, end = 24.dp),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var emailValue by remember { mutableStateOf("") }
            var passwordValue by remember { mutableStateOf("") }
            var isTermsCheck by remember { mutableStateOf(false)}

            val customTextFieldShape = RoundedCornerShape(4.dp)

            TextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                placeholder = { Text("E-mail") },
                label = { Text(text = "E-mail") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 24.dp))
            TextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                placeholder = { Text("Password") },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Row {
                Checkbox(checked = false, onCheckedChange ={isTermsCheck = it})
                Text(text = "By registering, you agree to the General Agreement, the Terms of Use and the Privacy Policy", color = Color(0xFFAFAFAF))
            }
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            Button(onClick = {
                             if(registerViewModel.Register()){
                                 navigator.navigate(direction = HomeViewDestination)
                             }
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                Text(text = "Register", fontSize = 24.sp,
                    modifier = Modifier.size(width = 128.dp, height = 36.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInformationsView(){
    var firstNameValue by remember { mutableStateOf("") }
    var lastNameValue by remember { mutableStateOf("") }
    var phoneNumberValue by remember { mutableStateOf("") }
    var addressValue by remember { mutableStateOf("") }
    var zipValue by remember { mutableStateOf("") }
    var cityValue by remember { mutableStateOf("") }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, bottom = 64.dp, start = 24.dp, end = 24.dp),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val customTextFieldShape = RoundedCornerShape(4.dp)
            TextField(
                value = firstNameValue,
                onValueChange = { firstNameValue = it },
                placeholder = { Text("First Name") },
                label = { Text(text = "First Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            TextField(
                value = lastNameValue,
                onValueChange = { lastNameValue = it },
                placeholder = { Text("Last Name") },
                label = { Text(text = "Last Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            TextField(
                value = addressValue,
                onValueChange = { addressValue = it },
                placeholder = { Text("Address") },
                label = { Text(text = "Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            TextField(
                value = cityValue,
                onValueChange = { cityValue = it },
                placeholder = { Text("City") },
                label = { Text(text = "City") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            TextField(
                value = zipValue,
                onValueChange = { zipValue = it },
                placeholder = { Text("Postal Code") },
                label = { Text(text = "Postal Code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                ),

                )
            Spacer(modifier = Modifier.padding(vertical = 18.dp))
            TextField(
                value = phoneNumberValue,
                onValueChange = { phoneNumberValue = it },
                placeholder = { Text("Phone") },
                label = { Text(text = "Phone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                shape = customTextFieldShape,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF536DFE),
                    focusedIndicatorColor = Color(0xFF536DFE),
                    cursorColor = Color(0xFF536DFE)
                )
                )
            Spacer(modifier = Modifier.padding(vertical = 24.dp))
            Button(onClick = { if(registerViewModel.Register()){} },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))) {
                Text(text = "Finish", fontSize = 24.sp,
                    modifier = Modifier.size(width = 128.dp, height = 36.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
}