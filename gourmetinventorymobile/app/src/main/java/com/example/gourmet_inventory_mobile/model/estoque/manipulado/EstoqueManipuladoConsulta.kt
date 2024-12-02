package com.example.gourmet_inventory_mobile.model.estoque.manipulado

import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
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
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val descricao: String,
    val receita: ReceitaConsultaDto,
)