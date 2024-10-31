package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
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
    @Contextual
    val dtaCadastro: LocalDate,
    @Contextual
    val dtaAviso: LocalDate,
    val marca: String
) : java.io.Serializable , android.os.Parcelable