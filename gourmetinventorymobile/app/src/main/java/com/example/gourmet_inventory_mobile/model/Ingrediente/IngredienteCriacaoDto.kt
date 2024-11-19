package com.example.gourmet_inventory_mobile.model.Ingrediente

import com.example.gourmet_inventory_mobile.model.Medidas

data class IngredienteCriacaoDto(
    val idItem: Long,
    val tipoMedida: Medidas,
    val valorMedida: Double
)