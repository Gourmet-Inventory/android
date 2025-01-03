package com.example.gourmet_inventory_mobile.model.Usuario

import com.example.gourmet_inventory_mobile.model.Empresa
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Long,
    val email : String,
    val senha : String,
    val cargo : String,
    val name : String,
    val telefone : String,
    val empresa : Empresa
)

data class UserResponse(val users: List<User>)