package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacaoDto
import retrofit2.Response
interface EstoqueRepository {
    suspend fun getEstoque(idEmpresa: Long) : Response<List<Map<String, Any>>>
    suspend fun createEstoque(idEmpresa: Long ,estoque: EstoqueCriacaoDto) : Response<EstoqueConsulta>
    suspend fun createEstoqueManipulado(idEmpresa: Long ,estoque: EstoqueCriacaoDto) : Response<EstoqueConsulta>
    suspend fun updateEstoque(idEmpresa: Long ,estoque: Estoque) : Response<EstoqueConsulta>
    suspend fun deleteEstoque(id: Long) : Response<Unit>
}