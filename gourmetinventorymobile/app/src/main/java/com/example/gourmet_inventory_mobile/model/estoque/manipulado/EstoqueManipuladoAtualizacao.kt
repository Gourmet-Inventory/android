package com.example.gourmet_inventory_mobile.model.estoque.manipulado

import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class EstoqueManipuladoAtualizacao (
    var estoqueIngredienteAtualizacaoDto: EstoqueIngredienteAtualizacaoDto? = null,
    var receita: List<IngredienteCriacaoDto>? = null
) : java.io.Serializable , android.os.Parcelable