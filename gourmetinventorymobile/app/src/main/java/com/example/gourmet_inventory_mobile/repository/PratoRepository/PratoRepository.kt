package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import retrofit2.Response

interface PratoRepository {
    suspend fun getPratos(idEmpresa: Long) : Response<List<Prato>>

}