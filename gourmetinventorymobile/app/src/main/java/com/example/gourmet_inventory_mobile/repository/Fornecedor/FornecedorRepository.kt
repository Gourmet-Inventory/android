package com.example.gourmet_inventory_mobile.repository.Fornecedor

import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.service.LoginResponse
import retrofit2.Response

interface FornecedorRepository {
    suspend fun obterFornecedores(): Response<List<Fornecedor>>
}