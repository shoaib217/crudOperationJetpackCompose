package com.example.crudoperation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.crudoperation.ui.table.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val uiState = mutableStateOf<UIState>(UIState.Loading)

    fun insert(userData: UserData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(User(
                userName = userData.name,
                userEmail = userData.email,
                userMobile = userData.mobile
            ))
        }
    }

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        repository.getUser().onStart {
            uiState.value = UIState.Loading

        }.catch {
            it.message?.let {errorMessage ->
                uiState.value = UIState.Failure(errorMessage)
            }
        }.collect{
            uiState.value = UIState.Success(it)
        }
    }
}

sealed class UIState{
    object Loading : UIState()
    class Success(val data: List<User>) : UIState()
    class Failure(val msg : String): UIState()
}

class MainViewModelFactory(val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}