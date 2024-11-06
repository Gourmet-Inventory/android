package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
data class EstoqueConsulta (
    val idItem: Long,
    val manipulado: Boolean,
    val lote : String,
    val nome: String,
    val categoria: String,
    val tipoMedida: Medidas,
    val unitario: Int,
    val valorMedida: Double,
    val valorTotal: Double,
    val localArmazenamento: String,
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val marca: String
) : java.io.Serializable , android.os.Parcelable {
    fun toEstoqueCriacao(): EstoqueCriacao {
        return EstoqueCriacao(
            lote = this.lote,
            nome = this.nome,
            categoria = this.categoria.toString(),
            tipoMedida = this.tipoMedida,
            unitario = this.unitario,
            valorMedida = this.valorMedida,
            localArmazenamento = this.localArmazenamento,
            dtaCadastro = this.dtaCadastro,
            dtaAviso = this.dtaAviso,
            marca = this.marca,
            manipulado = this.manipulado
        )
    }
}