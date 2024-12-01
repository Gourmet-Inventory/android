package com.example.gourmet_inventory_mobile.model.estoque.industrializado

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
data class EstoqueCriacaoDto(
    var lote: String,
    var manipulado: Boolean,
    var nome: String,
    var categoria: CategoriaEstoque,
    var tipoMedida: Medidas,
    var unitario: Int,
    var valorMedida: Double,
    var localArmazenamento: String,
    @Contextual
    var dtaCadastro: LocalDate,
    @Contextual
    var dtaAviso: LocalDate,
    var marca: String
) : java.io.Serializable , android.os.Parcelable {

    fun toEstoque(empresa: Empresa): Estoque {
        return Estoque(
            lote = this.lote,
            nome = this.nome,
            categoria = this.categoria,
            tipoMedida = this.tipoMedida,
            unitario = this.unitario,
            valorMedida = this.valorMedida,
            localArmazenamento = this.localArmazenamento,
            dtaCadastro = this.dtaCadastro,
            dtaAviso = this.dtaAviso,
            marca = this.marca,
            manipulado = this.manipulado,
            empresa = empresa
        )
    }

    fun toEstoqueAtualizacao(): EstoqueIngredienteAtualizacaoDto {
        return EstoqueIngredienteAtualizacaoDto(
            lote = this.lote,
            nome = this.nome,
            categoria = this.categoria,
            tipoMedida = this.tipoMedida,
            unitario = this.unitario,
            valorMedida = this.valorMedida,
            localArmazenamento = this.localArmazenamento,
            dtaCadastro = this.dtaCadastro,
            dtaAviso = this.dtaAviso,
            marca = this.marca,
            descricao = "",
            valorTotal = this.unitario * this.valorMedida
        )
    }
}