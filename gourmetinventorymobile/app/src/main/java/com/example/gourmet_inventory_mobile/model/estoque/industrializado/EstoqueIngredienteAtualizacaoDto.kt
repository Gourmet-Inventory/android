package com.example.gourmet_inventory_mobile.model.estoque.industrializado

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
data class EstoqueIngredienteAtualizacaoDto(
    val nome: String,
    val lote: String,
    val marca: String,
    val categoria: CategoriaEstoque,
    val tipoMedida: Medidas,
    val unitario: Int,
    val valorMedida: Double,
    val valorTotal: Double,
    val localArmazenamento: String,
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val descricao: String
) : java.io.Serializable , android.os.Parcelable