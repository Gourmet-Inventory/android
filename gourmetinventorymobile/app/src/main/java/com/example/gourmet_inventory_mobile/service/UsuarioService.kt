package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.Empresa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

//val retrofit = Retrofit.Builder().baseUrl("")
//    .addConverterFactory(GsonConverterFactory.create()).build()

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val userId: String,
    val idUsuario : Long,
    val nome : String,
    val email : String,
    val cargo : String,
    val empresa : Empresa,
    val celular : String
)

interface UsuarioService {
    @POST("/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
