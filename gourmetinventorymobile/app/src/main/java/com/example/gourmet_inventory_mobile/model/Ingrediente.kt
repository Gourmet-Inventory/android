package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Ingrediente (
    val nome: String,
    val tipoMedida: Medidas,
    val valorMedida: Double,
    val exibirConca: String
) : Parcelable