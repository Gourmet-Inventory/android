package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.LoginRequest
import com.example.gourmet_inventory_mobile.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {
    @POST("/api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
