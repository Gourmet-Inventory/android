package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Parcelize
data class EstoqueCriacao (
    var lote : String,
    var manipulado: Boolean,
    var nome: String,
    var categoria: String,
    var tipoMedida: Medidas,
    var unitario: Int,
    var valorMedida: Double,
    var localArmazenamento: String,
    @Contextual
    var dtaCadastro: LocalDate,
    @Contextual
    var dtaAviso: LocalDate,
    var marca: String
) : java.io.Serializable , android.os.Parcelable