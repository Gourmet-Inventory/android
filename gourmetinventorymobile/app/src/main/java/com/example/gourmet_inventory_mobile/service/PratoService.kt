package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import com.example.gourmet_inventory_mobile.model.Prato
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PratoService {
    @GET("/api/pratos/{idEmpresa}")
    suspend fun getPratos(@Path("idEmpresa") idEmpresa: Long): Response<List<Prato>>
}