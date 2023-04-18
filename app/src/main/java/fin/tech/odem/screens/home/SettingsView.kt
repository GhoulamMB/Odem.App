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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import fin.tech.odem.R
import fin.tech.odem.screens.BottomBar
import fin.tech.odem.screens.destinations.HomeViewDestination
import fin.tech.odem.utils.AppClient

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(navigator: DestinationsNavigator) {
    var isEditEnabled by remember {mutableStateOf(false)}
    var fullName by remember {mutableStateOf("")}
    Box(modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 8.dp)){
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Transparent)
                .padding(start = 12.dp, end = 8.dp)
            ){
                Text(text = "⚠️ Please note that changing personal data can be done only via a support ticket.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterStart))
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = { isEditEnabled = true }, modifier = Modifier.align(Alignment.End)) {
                    Text(text = "Edit", color = Color(0xFF536DFE))
                }
                Text(text = "${AppClient.client.firstName} ${AppClient.client.lastName}", color = Color.White, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(text = "Address", color = Color.White, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                TextField(value = AppClient.client.email,
                    enabled = isEditEnabled,
                    onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF536DFE),
                        focusedIndicatorColor = Color(0xFF536DFE),
                        cursorColor = Color(0xFF536DFE)
                    ),
                    textStyle = TextStyle(color = Color.White, textAlign = TextAlign.Start)
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                /*
                * Uncomment Password visual transformation of this TextField
                * */
                TextField(value = "",
                    enabled = isEditEnabled,
                    onValueChange = {},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedLabelColor = Color(0xFF536DFE),
                        focusedIndicatorColor = Color(0xFF536DFE),
                        cursorColor = Color(0xFF536DFE)
                    ),
                    textStyle = TextStyle(color = Color.White, textAlign = TextAlign.Start),
                    //visualTransformation = PasswordVisualTransformation()
                )
            }
        }
        Box(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
            BottomBar(navigator)
        }
    }
}