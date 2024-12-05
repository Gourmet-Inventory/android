package com.example.gourmet_inventory_mobile.model.Ingrediente

import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class IngredienteCriacaoDto(
    val idItem: Long,
    val tipoMedida: Medidas,
    val valorMedida: Double
) : java.io.Serializable , android.os.Parcelable