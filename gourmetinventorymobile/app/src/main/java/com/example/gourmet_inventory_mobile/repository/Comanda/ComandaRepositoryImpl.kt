package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.service.ComandaService
import retrofit2.Response

class ComandaRepositoryImpl(private val service: ComandaService) : ComandaRepository {

    override suspend fun getAllComandas(): Response<List<Comanda>> {
        return service.getAllComandas()
    }

    override suspend fun getLastComanda(): Response<Comanda> {
        return service.getLastComanda()
    }

    override suspend fun createComanda(comanda: Comanda): Response<Comanda> {
        return service.createComanda(comanda)
    }

    override suspend fun updateComandaStatus(id: Long, status: String): Response<Comanda> {
        return service.updateComandaStatus(id, status)
    }

    override suspend fun deleteComanda(id: Long): Response<Unit> {
        return service.deleteComanda(id)
    }
}