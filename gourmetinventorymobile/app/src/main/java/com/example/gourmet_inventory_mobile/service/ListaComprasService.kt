package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ListaComprasService {

    @GET("/api/ListaCompras/listaCompras/{idEmpresa}")
    suspend fun getListaCompras(@Path("idEmpresa") idEmpresa: Long): Response<List<ItemListaCompras>>

    @DELETE("/api/ListaCompras/{idItem}")
    suspend fun deleteItem(
        @Path("idItem") idItem: Long
    ) : Response<Unit>

}