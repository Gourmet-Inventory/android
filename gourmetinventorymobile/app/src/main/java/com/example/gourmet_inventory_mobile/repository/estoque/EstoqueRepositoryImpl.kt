package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoAtualizacao
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoConsulta
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
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
        estoque: EstoqueManipuladoCriacao
    ): Response<EstoqueManipuladoConsulta> {
        return serviceEstoque.createEstoqueManipulado(idEmpresa, estoque)
    }

    override suspend fun updateEstoque(
        idEstoque: Long,
        estoque: EstoqueIngredienteAtualizacaoDto
    ): Response<EstoqueConsulta> {
        return serviceEstoque.updateEstoque(idEstoque, estoque)
    }

    override suspend fun updateEstoqueManipulado(
        idEstoque: Long,
        estoque: EstoqueManipuladoAtualizacao
    ): Response<EstoqueManipuladoConsulta> {
        return serviceEstoque.updateEstoqueManipulado(idEstoque, estoque)
    }

    override suspend fun deleteEstoque(id: Long): Response<Unit> {
        return serviceEstoque.deleteEstoque(id)
    }
}