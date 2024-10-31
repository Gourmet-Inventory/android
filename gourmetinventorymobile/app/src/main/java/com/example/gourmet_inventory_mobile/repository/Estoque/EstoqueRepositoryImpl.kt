package com.example.gourmet_inventory_mobile.repository.Estoque

import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.service.EstoqueService
import retrofit2.Response

class EstoqueRepositoryImpl(private val serviceEstoque: EstoqueService) :
    EstoqueRepository {
    override suspend fun obterEstoque(): Response<List<EstoqueConsulta>> {
        return serviceEstoque.getEstoque()
    }
}