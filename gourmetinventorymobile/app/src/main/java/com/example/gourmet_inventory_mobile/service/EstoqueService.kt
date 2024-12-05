package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoAtualizacao
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoConsulta
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EstoqueService {
    @GET("/api/estoque-ingrediente/{idEmpresa}")
    suspend fun getEstoque(@Path("idEmpresa") idEmpresa: Long): Response<List<Map<String, Any>>>
//    suspend fun getEstoque(@Path("idEmpresa") idEmpresa: Long): Response<List<EstoqueManipuladoConsulta>>

    @POST("/api/estoque-ingrediente/{idEmpresa}")
    suspend fun createEstoque(
        @Path("idEmpresa") idEmpresa: Long,
        @Body estoque: EstoqueCriacaoDto
    ): Response<EstoqueConsulta>

    @PUT("/api/estoque-ingrediente/{id}")
    suspend fun updateEstoque(
        @Path("id") idEstoque: Long,
        @Body estoque: EstoqueIngredienteAtualizacaoDto
    ) : Response<EstoqueConsulta>

    @PUT("/atualizar-estoque-manipulado/{id}")
    suspend fun updateEstoqueManipulado(
        @Path("id") idEstoque: Long,
        @Body estoque: EstoqueManipuladoAtualizacao
    ) : Response<EstoqueManipuladoConsulta>

    @DELETE("/api/estoque-ingrediente/{id}")
    suspend fun deleteEstoque(
        @Path("id") idItem: Long
    ) : Response<Unit>

    @POST("/api/estoque-ingrediente/manipulado/{idEmpresa}")
    suspend fun createEstoqueManipulado(
        @Path("idEmpresa") idEmpresa: Long,
        @Body estoque: EstoqueManipuladoCriacao
    ): Response<EstoqueManipuladoConsulta>
}