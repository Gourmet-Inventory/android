package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.User
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()        
}

class LoginViewModel (private val usuarioRepository: UsuarioRepository): ViewModel() {
//class LoginViewModel: ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(context: Context, email: String, senha: String) {
        Log.d("LoginViewModel", "Email: $email, Password: $senha")

        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                //fazendo a chamada para o login
//                val response = serviceUsuario.login(LoginRequest(email, password))
                val response = usuarioRepository.login(email, senha)
                Log.d("LoginViewModel", "Response: $response")
                Log.d("LoginViewModel", "Response Body: ${response.body().toString()}")
                Log.d("LoginViewModel", "Response Erro Body: ${response.errorBody().toString()}")

                if (response.isSuccessful && response.body() != null) {
                    Log.d("LoginViewModel", "Login realizado com sucesso e corpo da resposta não é nulo")
                    val loginResponse = response.body()!!

                    val user = User(
                        email = loginResponse.email,
                        senha = senha,
                        cargo = loginResponse.cargo,
                        name = loginResponse.nome,
                        telefone = loginResponse.celular
                    )
                    Log.d("LoginViewModel", "User: $user")

                    //salvar o usuario no dataStore
                    DataStoreUtils(context).salvarUsusario(user)
                    //salvar o token no dataStore
                    DataStoreUtils(context).salvarToken(loginResponse.token)
                    _loginState.value = LoginState.Success(user)

                } else {
                    Log.d("LoginViewModel", "Erro ao fazer login")
                    _loginState.value = LoginState.Error("Erro ao fazer login")
                }

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}