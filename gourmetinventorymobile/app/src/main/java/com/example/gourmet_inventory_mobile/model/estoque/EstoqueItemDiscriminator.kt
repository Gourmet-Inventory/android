package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
sealed class EstoqueItemDiscriminator{
    abstract val idItem: Long
    abstract val manipulado: Boolean
    abstract val unitario: Int
    abstract val dtaAviso: LocalDate
    abstract val nome: String

    @Serializable
    data class Industrializado(
        override val idItem: Long,
        override val manipulado: Boolean,
        val lote : String,
        override val nome: String,
        val categoria: CategoriaEstoque,
        val tipoMedida: Medidas,
        override val unitario: Int,
        val valorMedida: Double,
        val valorTotal: Double,
        val localArmazenamento: String,
        @Contextual
        val dtaCadastro: LocalDate,
        @Contextual
        override val dtaAviso: LocalDate,
        val marca: String
    ) : EstoqueItemDiscriminator()

    @Serializable
    data class Manipulado(
        override val idItem: Long,
        override val manipulado: Boolean,
        override val nome: String,
        val lote: String,
        val categoria: CategoriaEstoque, // CategoriaEstoque (enum)
        val tipoMedida: Medidas, // Medidas (enum)
        override val unitario: Int,
        val valorMedida: Double,
        val valorTotal: Double,
        val localArmazenamento: String,
        @Contextual
        val dtaCadastro: LocalDate,
        @Contextual
        override val dtaAviso: LocalDate,
        val descricao: String,
        @Contextual
        val receita: ReceitaConsultaDto,
    ) : EstoqueItemDiscriminator()
}

data class Receita(
    val nomeReceita: String,
    val ingredientes: List<String>
)
