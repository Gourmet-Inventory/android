package com.example.gourmet_inventory_mobile.model.estoque.manipulado

import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto

data class EstoqueManipuladoCriacao(
    val estoqueIngredienteCriacaoDto: EstoqueCriacaoDto,
    val receita: List<IngredienteCriacaoDto>
)