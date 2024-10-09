package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email : String,
    val password : String,
    val role : String
) : Parcelable

data class UserResponse(val users: List<User>)