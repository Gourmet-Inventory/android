package com.example.gourmet_inventory_mobile.model.Ingrediente

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Ingrediente (
    val nome: String?,
    val tipoMedida: String,
    val valorMedida: Double,
    val exibirConca: String
) : Parcelable