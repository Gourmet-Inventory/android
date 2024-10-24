package com.example.gourmet_inventory_mobile.repository.Usuario

import com.example.gourmet_inventory_mobile.service.LoginRequest
import com.example.gourmet_inventory_mobile.service.LoginResponse
import com.example.gourmet_inventory_mobile.service.UsuarioService
import retrofit2.Response

class UsuarioRepositoryImpl (private val serviceUsuario: UsuarioService) : UsuarioRepository {

    override suspend fun login(email: String, senha: String): Response<LoginResponse> {
        return serviceUsuario.login(request =  LoginRequest(email, senha))
    }

}