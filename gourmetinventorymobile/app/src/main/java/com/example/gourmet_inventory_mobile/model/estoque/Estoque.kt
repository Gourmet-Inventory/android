package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Medidas
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Estoque(
    val idItem: Long? = null,
    val lote: String,
    val manipulado: Boolean? = null,
    val nome: String,
    val empresa: Empresa,
    val categoria: CategoriaEstoque,
    val tipoMedida: Medidas,
    val unitario: Int? = null,
    val valorMedida: Double,
    val valorTotal: Double? = null,
    val localArmazenamento: String? = null,
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val marca: String
)
