package com.example.gourmet_inventory_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email : String,
    val password : String,
    val role : String,
    val name : String,
    val phone : String,
)

data class UserResponse(val users: List<User>)