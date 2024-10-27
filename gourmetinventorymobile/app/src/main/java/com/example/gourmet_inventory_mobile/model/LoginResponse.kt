package com.example.gourmet_inventory_mobile.model

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