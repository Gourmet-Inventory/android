package com.example.gourmet_inventory_mobile.model.Receita

import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto

data class ReceitaCriacaoDto(
    val idItem: Long,
    val receita: List<IngredienteCriacaoDto>
)