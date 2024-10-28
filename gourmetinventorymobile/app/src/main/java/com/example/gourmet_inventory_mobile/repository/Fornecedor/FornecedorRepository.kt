package com.example.gourmet_inventory_mobile.repository.Fornecedor

import com.example.gourmet_inventory_mobile.model.Fornecedor
import retrofit2.Response

interface FornecedorRepository {
    suspend fun obterFornecedores(): Response<List<Fornecedor>>
}