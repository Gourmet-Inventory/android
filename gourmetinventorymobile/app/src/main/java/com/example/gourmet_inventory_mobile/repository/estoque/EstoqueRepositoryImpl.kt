package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.service.EstoqueService
import retrofit2.Response

class EstoqueRepositoryImpl(private val serviceEstoque: EstoqueService) : EstoqueRepository {

    override suspend fun getEstoque(idEmpresa: Long): Response<List<EstoqueConsulta>> {
        return serviceEstoque.getEstoque(idEmpresa)
    }

    override suspend fun createEstoque(idEmpresa: Long, estoque: EstoqueCriacao) : Response<EstoqueConsulta>{
        return serviceEstoque.createEstoque(idEmpresa, estoque)
    }

    override suspend fun updateEstoque(
        idEmpresa: Long,
        estoque: Estoque
    ): Response<EstoqueConsulta> {
        return serviceEstoque.updateEstoque(idEmpresa, estoque)
    }
}