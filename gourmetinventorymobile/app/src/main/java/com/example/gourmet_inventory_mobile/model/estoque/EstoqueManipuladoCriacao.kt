package com.example.gourmet_inventory_mobile.model.estoque

import com.example.gourmet_inventory_mobile.model.Receita.ReceitaCriacaoDto

data class EstoqueManipuladoCriacao(
    val estoqueIngredienteCriacaoDto: EstoqueCriacaoDto,
    val receitaCriacaoDto: ReceitaCriacaoDto
)