package com.example.gourmet_inventory_mobile.repository.Usuario

import com.example.gourmet_inventory_mobile.model.Usuario.LoginResponse
import retrofit2.Response

interface UsuarioRepository{
    suspend fun login(email: String, password: String): Response<LoginResponse>
}