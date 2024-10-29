package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.Fornecedor
import retrofit2.Response
import retrofit2.http.GET

interface EstoqueService {
    @GET("/estoque-ingrediente/{idEmpresa}")
    suspend fun getEstoque(): Response<List<EstoqueConsulta>>
}