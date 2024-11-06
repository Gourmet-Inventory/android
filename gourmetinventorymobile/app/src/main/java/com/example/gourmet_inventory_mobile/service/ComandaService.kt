package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.Comanda
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

interface ComandaService {
    @GET("/api/comandas")
    suspend fun getAllComandas(): Response<List<Comanda>>

    @GET("/api/comandas/last")
    suspend fun getLastComanda(): Response<Comanda>

    @POST("/api/comandas")
    suspend fun createComanda(
        @Body comanda: Comanda
    ): Response<Comanda>

    @PUT("/api/comandas/{id}")
    suspend fun updateComanda(
        @Path("id") id: Long,
        @Body updatedComanda: Comanda
    ) : Response<Comanda>

    @DELETE("/api/comandas/{id}")
    suspend fun deleteComanda(
        @Path("id") id: Long
    ) : Response<Unit>
}