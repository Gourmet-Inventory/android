package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListaComprasService {

    @GET("/api/ListaCompras/listaCompras")
    suspend fun getListaCompras(@Path("idEmpresa") idEmpresa: Long): Response<List<ItemListaCompras>>

}