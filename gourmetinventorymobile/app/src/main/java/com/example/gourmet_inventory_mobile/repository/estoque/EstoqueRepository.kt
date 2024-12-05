package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoAtualizacao
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoConsulta
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
import retrofit2.Response

interface EstoqueRepository {
    suspend fun getEstoque(idEmpresa: Long) : Response<List<Map<String, Any>>>
    suspend fun createEstoque(idEmpresa: Long ,estoque: EstoqueCriacaoDto) : Response<EstoqueConsulta>
    suspend fun createEstoqueManipulado(idEmpresa: Long ,estoque: EstoqueManipuladoCriacao) : Response<EstoqueManipuladoConsulta>
    suspend fun updateEstoque(idEmpresa: Long ,estoque: EstoqueIngredienteAtualizacaoDto) : Response<EstoqueConsulta>
    suspend fun updateEstoqueManipulado(idEstoque: Long, estoque: EstoqueManipuladoAtualizacao) : Response<EstoqueManipuladoConsulta>
    suspend fun deleteEstoque(id: Long) : Response<Unit>
}