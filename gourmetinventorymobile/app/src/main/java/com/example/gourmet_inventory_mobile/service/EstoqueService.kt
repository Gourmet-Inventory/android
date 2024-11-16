package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EstoqueService {
    @GET("/api/estoque-ingrediente/{idEmpresa}")
    suspend fun getEstoque(@Path("idEmpresa") idEmpresa: Long): Response<List<EstoqueConsulta>>

    @POST("/api/estoque-ingrediente/{idEmpresa}")
    suspend fun createEstoque(
        @Path("idEmpresa") idEmpresa: Long,
        @Body estoque: EstoqueCriacao
    ): Response<EstoqueConsulta>

    @PUT("/api/estoque-ingrediente/atualizar-estoque/{id}")
    suspend fun updateEstoque(
        @Path("id") idEstoque: Long,
        @Body estoque: Estoque
    ) : Response<EstoqueConsulta>

    @DELETE("/api/estoque-ingrediente/deletar-item/{id}")
    suspend fun deleteEstoque(
        @Path("id") idItem: Long
    ) : Response<Unit>
}