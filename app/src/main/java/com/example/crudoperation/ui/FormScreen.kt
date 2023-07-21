package com.example.crudoperation.ui

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel,
) {
    val context = LocalContext.current.applicationContext
    Scaffold(topBar = {
        AppToolBar(toolbarTitle = "User Form")
    }) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            val name = rememberSaveable {
                mutableStateOf("")
            }
            val email = rememberSaveable {
                mutableStateOf("")
            }
            val mobile = rememberSaveable {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), value = name.value, onValueChange = { newValue ->
                    name.value = newValue
                }, label = { Text(text = "Name") }, placeholder = {
                    Text(
                        text = "Please Enter Name"
                    )
                })
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), value = email.value, onValueChange = { newValue ->
                    email.value = newValue
                }, label = { Text(text = "Email") }, placeholder = {
                    Text(
                        text = "Please Enter Email"
                    )
                })
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp), value = mobile.value, onValueChange = { newValue ->
                    mobile.value = newValue
                }, label = { Text(text = "Mobile") }, placeholder = {
                    Text(
                        text = "Please Enter Mobile"
                    )
                })
                Button(onClick = {
                    if (validate(name.value, email.value, mobile.value, context)) {
                        mainViewModel.insert(UserData(name.value, email.value, mobile.value))
                        navController.popBackStack()
                    }
                }) {
                    Text(
                        text = "Save",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

fun validate(name: String, email: String, mobile: String, context: Context): Boolean {
    if (name.trim().isEmpty()) {
        Toast.makeText(context, "name cannot be blank", Toast.LENGTH_SHORT).show()
        return false
    }
    if (email.trim().isEmpty()) {
        Toast.makeText(context, "email cannot be blank", Toast.LENGTH_SHORT).show()
        return false
    }
    if (mobile.trim().isEmpty()) {
        Toast.makeText(context, "mobile cannot be blank", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun FormScreenPreview() {
    FormScreen(navController = rememberNavController(), mainViewModel = viewModel())
}