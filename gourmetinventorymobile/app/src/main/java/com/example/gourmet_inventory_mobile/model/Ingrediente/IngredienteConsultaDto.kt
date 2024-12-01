package com.example.gourmet_inventory_mobile.model.Ingrediente

import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.serialization.Serializable

@Serializable
data class IngredienteConsultaDto(
    val nome: String,
    val tipoMedida: Medidas,
    val valorMedida: Double,
    val exibirConca: String
){
    constructor(nome: String, tipoMedida: Medidas, valorMedida: Double) : this(
        nome,
        tipoMedida,
        valorMedida,
        valorMedida.toString() + "-" + tipoMedida
    )
}
