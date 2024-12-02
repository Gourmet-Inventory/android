package com.example.gourmet_inventory_mobile.model.estoque.industrializado

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
data class EstoqueConsulta(
    val idItem: Long,
    val manipulado: Boolean,
    val lote: String,
    val nome: String,
    val categoria: String,
    val tipoMedida: String,
    val unitario: Int,
    val valorMedida: Double,
    val valorTotal: Double,
    val localArmazenamento: String,
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val marca: String
) : java.io.Serializable, android.os.Parcelable {
    fun toEstoqueCriacao(): EstoqueCriacaoDto {
        return EstoqueCriacaoDto(
            lote = this.lote,
            nome = this.nome,
            categoria = parseCategoria(this.categoria),
            tipoMedida = parseMedida(this.tipoMedida),
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
fun parseCategoria(categoria: String): CategoriaEstoque {
    return CategoriaEstoque.values().find {
        it.nomeExibicao.equals(categoria, ignoreCase = true)
    } ?: throw IllegalArgumentException("Categoria inválida: $categoria")
}

fun parseMedida(tipoMedida: String): Medidas {
    return Medidas.values().find {
        it.name.equals(tipoMedida, ignoreCase = true)
    } ?: throw IllegalArgumentException("Medida inválida: $tipoMedida")
}