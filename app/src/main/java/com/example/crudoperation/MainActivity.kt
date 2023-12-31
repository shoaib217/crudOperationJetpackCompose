package com.example.crudoperation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.crudoperation.ui.MainViewModel
import com.example.crudoperation.ui.MainViewModelFactory
import com.example.crudoperation.ui.MyDatabase
import com.example.crudoperation.ui.Repository
import com.example.crudoperation.ui.SetupNavGraph


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myDatabase = MyDatabase.getDatabase(context = applicationContext)
        val repository = Repository(myDatabase.getDao())
        val mainViewModel by viewModels<MainViewModel> (factoryProducer = { MainViewModelFactory(repository = repository) })
        setContent {
            AppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController,mainViewModel) {
                    onBackPress()
                }
            }
        }
    }

    private fun onBackPress() {
        if (doubleBackToExitPressedOnce){
            finish()
        }

        if (!doubleBackToExitPressedOnce){
            doubleBackToExitPressedOnce = true
            Toast.makeText(
                this,
                "Please click BACK again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        },2000)
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SetUpNavigationPreview(){
    AppTheme {
        SetupNavGraph(navController = rememberNavController()) {}
    }
}
