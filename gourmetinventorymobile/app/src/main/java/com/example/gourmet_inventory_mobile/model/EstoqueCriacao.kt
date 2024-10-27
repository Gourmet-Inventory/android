package com.example.gourmet_inventory_mobile.model

import java.time.LocalDate

data class EstoqueCriacao (
    val lote : String,
    val manipulado: Boolean,
    val nome: String,
    val categoria: String,
    val tipoMedida: Medidas,
    val unitario: Int,
    val valorMedida: Double,
    val localArmazenamento: String,
    val dtaCadastro: LocalDate,
    val dtaAviso: LocalDate
)