package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Comanda
import retrofit2.Response

interface ComandaRepository {
    suspend fun getAllComandas() : Response<List<Comanda>>

    suspend fun getLastComanda() : Response<Comanda>

    suspend fun createComanda(comanda: Comanda) : Response<Comanda>

    suspend fun updateComandaStatus(id: Long, status: String) : Response<Comanda>

    suspend fun deleteComanda(id: Long) : Response<Unit>
}