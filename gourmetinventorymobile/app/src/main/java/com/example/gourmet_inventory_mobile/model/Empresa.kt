package com.example.gourmet_inventory_mobile.model

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Empresa (
    val idEmpresa: Long,
    val nomeFantasia: String,
) : java.io.Serializable , android.os.Parcelable