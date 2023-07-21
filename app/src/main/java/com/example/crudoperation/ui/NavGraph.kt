package com.example.crudoperation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    backPressed: ()-> Unit
) {
    NavHost(navController = navController, startDestination = Screen.UserList.route) {
        composable(Screen.UserList.route) {
            UserList(navController,mainViewModel = mainViewModel,backPressed)
        }
        composable(Screen.FormScreen.route) {
            FormScreen(navController,mainViewModel = mainViewModel)
        }
    }
}