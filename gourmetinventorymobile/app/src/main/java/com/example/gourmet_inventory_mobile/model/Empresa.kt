package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Empresa (
    val idEmpresa: Long,
    val nomeFantasia: String,
) : Parcelable