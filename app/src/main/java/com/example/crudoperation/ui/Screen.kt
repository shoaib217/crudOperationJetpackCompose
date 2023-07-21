package com.example.crudoperation.ui

sealed class Screen(val route: String) {
    object UserList : Screen(route = "UserList")
    object FormScreen : Screen(route = "FormScreen")
}