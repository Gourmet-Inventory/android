package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.service.ComandaService
import com.example.gourmet_inventory_mobile.service.EstoqueService
import retrofit2.Response

class ComandaRepositoryImpl(private val service: ComandaService) : ComandaRepository {

    override suspend fun getAllComandas(): Response<List<Comanda>> {
        return service.getAllComandas()
    }
}