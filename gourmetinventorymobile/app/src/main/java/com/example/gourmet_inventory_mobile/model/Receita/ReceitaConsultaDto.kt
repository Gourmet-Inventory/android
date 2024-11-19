package com.example.gourmet_inventory_mobile.model.Receita

import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteConsultaDto

data class ReceitaConsultaDto(
    val idReceita: Long,
    val receita: List<IngredienteConsultaDto>
)