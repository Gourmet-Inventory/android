package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Comanda(
    val mesa: String,
    val nomeCliente: String,
    val nomeComanda: String,
    val garcom : User,
) : Parcelable