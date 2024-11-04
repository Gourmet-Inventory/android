package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Comanda(
    val id: Long,
    val titulo: String,
    val mesa: String,
    val itens: List<Prato>,
    val status: String,
    val total: Double
)