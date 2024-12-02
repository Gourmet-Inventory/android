package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import com.example.gourmet_inventory_mobile.model.Ingrediente.Ingrediente
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Prato(
    val idPrato: Long,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val alergicosRestricoes: List<String>,
    val categoria: String,
//    val receitaPrato: List<Ingrediente>,
    val foto: String?,
    val URLAssinada: String?
): Parcelable

data class PratoResponse(val pratos: List<Prato>)