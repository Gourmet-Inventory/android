package com.example.gourmet_inventory_mobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.network.LoginRequest
import com.example.gourmet_inventory_mobile.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String , val cargo : String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = RetrofitInstance.apiUser.login(LoginRequest(email, password))

                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()!!.token
                    val cargo = response.body()!!.cargo
                    _loginState.value = LoginState.Success(token, cargo)
                } else {
                    _loginState.value = LoginState.Error("Erro ao fazer login")
                }

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}