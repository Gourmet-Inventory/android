package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import java.time.LocalDate

data class EstoqueManipuladoConsulta(
    val idItem: Long,
    val manipulado: Boolean,
    val nome: String,
    val lote: String,
    val categoria: String, // CategoriaEstoque (enum)
    val tipoMedida: String, // Medidas (enum)
    val unitario: Int,
    val valorMedida: Double,
    val valorTotal: Double,
    val localArmazenamento: String,
    val dtaCadastro: LocalDate,
    val dtaAviso: LocalDate,
    val descricao: String,
    val receita: ReceitaConsultaDto,
)