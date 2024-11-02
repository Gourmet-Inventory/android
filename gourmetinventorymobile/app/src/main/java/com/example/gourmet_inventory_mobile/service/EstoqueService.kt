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
    @GET("/estoque-ingrediente/{idEmpresa}")
    suspend fun getEstoque(@Path("idEmpresa") idEmpresa: Long): Response<List<EstoqueConsulta>>

    @POST("/estoque-ingrediente/{idEmpresa}")
    suspend fun createEstoque(
        @Path("idEmpresa") idEmpresa: Long,
        @Body estoque: EstoqueCriacao
    ): Response<EstoqueConsulta>

    @PUT("/estoque-ingrediente/{idEmpresa}")
    suspend fun updateEstoque(
        @Path("idEmpresa") idEmpresa: Long,
        @Body estoque: Estoque
    ) : Response<EstoqueConsulta>

    @DELETE("/estoque-ingrediente/deletar-item/{id}")
    suspend fun deleteEstoque(
        @Path("id") idItem: Long
    ) : Response<Unit>
}