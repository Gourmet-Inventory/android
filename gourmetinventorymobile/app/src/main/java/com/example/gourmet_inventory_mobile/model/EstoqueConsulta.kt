package com.example.gourmet_inventory_mobile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.LocalDate

data class EstoqueConsulta (
    val idItem: Long,
    val empresa: Empresa,
    val manipulado: Boolean,
    val lote : String,
    val nome: String,
    val categoria: String,
    val tipoMedida: Medidas,
    val unitario: Int,
    val valorMedida: Double,
    val valorTotal: Double,
    val localArmazenamento: String,
    val dtaCadastro: LocalDate,
    val dtaAviso: LocalDate
)

data class EstoqueConsultaResponse(val listaEstoque: List<EstoqueConsulta>)

