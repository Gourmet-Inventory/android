package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Comanda(
    val id: Long? = null,
    val idGarcom: Long,
    var titulo: String,
    var mesa: String,
    val itens: List<Prato>,
    var status: String,
    val total: Double
)