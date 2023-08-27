package com.example.crudoperation.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.crudoperation.MainActivity
import com.example.crudoperation.ui.table.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    backPressed: () -> Unit
) {
    BackHandler(true,backPressed)
    Scaffold(topBar = {
        AppToolBar(toolbarTitle = "Users")
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.FormScreen.route) }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            when (val result = mainViewModel.uiState.value) {
                is UIState.Loading -> ShowProgressBar()
                is UIState.Success -> ShowRecyclerView(result.data)
                is UIState.Failure -> ShowErrorScreen(result.msg)
            }
        }

    }
}

@Composable
fun ShowProgressBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowRecyclerView(users: List<User>) {
    if (users.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No Data Found!")
        }
    } else {
        LazyColumn() {
            items(users) { user ->
                ShowUserCard(UserData(user.userName, user.userEmail, user.userMobile))
            }
        }
    }
}

@Composable
fun ShowUserCard(user: UserData) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name: ${user.name}")
            Text(text = "Email:  ${user.email}")
            Text(text = "Mobile No.: ${user.mobile}")
        }
    }
}

@Composable
fun ShowErrorScreen(msg: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = msg)
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun UserListPreview() {
    UserList(navController = rememberNavController(), mainViewModel = viewModel()) {}
}