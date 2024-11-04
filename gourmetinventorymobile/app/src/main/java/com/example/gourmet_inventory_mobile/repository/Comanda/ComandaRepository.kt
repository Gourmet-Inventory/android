package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import retrofit2.Response

interface ComandaRepository {
    suspend fun getAllComandas() : Response<List<Comanda>>

//    suspend fun createComanda(comanda: Comanda) : Response<Comanda>
//
//    suspend fun updateComanda(id: Long, updatedComanda: Comanda) : Response<Comanda>
//
//    suspend fun deleteComanda(id: Long) : Response<Unit>

}