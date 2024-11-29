package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.service.EstoqueService
import retrofit2.Response

class EstoqueRepositoryImpl(private val serviceEstoque: EstoqueService) : EstoqueRepository {
    override suspend fun getEstoque(idEmpresa: Long): Response<List<Map<String, Any>>> {
        return serviceEstoque.getEstoque(idEmpresa)
    }

    override suspend fun createEstoque(
        idEmpresa: Long,
        estoque: EstoqueCriacaoDto
    ): Response<EstoqueConsulta> {
        return serviceEstoque.createEstoque(idEmpresa, estoque)
    }

    override suspend fun createEstoqueManipulado(
        idEmpresa: Long,
        estoque: EstoqueCriacaoDto
    ): Response<EstoqueConsulta> {
        return serviceEstoque.createEstoqueManipulado(idEmpresa, estoque)
    }

    override suspend fun updateEstoque(
        idEstoque: Long,
        estoque: Estoque
    ): Response<EstoqueConsulta> {
        return serviceEstoque.updateEstoque(idEstoque, estoque)
    }

    override suspend fun deleteEstoque(id: Long): Response<Unit> {
        return serviceEstoque.deleteEstoque(id)
    }
}