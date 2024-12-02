package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class ItemListaCompras (
    val idItemLista: Long,
    val nome: String,
    val estoqueIngrediente: Estoque
)