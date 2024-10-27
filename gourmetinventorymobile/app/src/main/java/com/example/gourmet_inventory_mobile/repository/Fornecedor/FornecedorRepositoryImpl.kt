package com.example.gourmet_inventory_mobile.repository.Fornecedor

import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.service.FornecedorService
import retrofit2.Response

class FornecedorRepositoryImpl(private val serviceFornecedor: FornecedorService) : FornecedorRepository {
    override suspend fun obterFornecedores(): Response<List<Fornecedor>> {
        return serviceFornecedor.getFornecedores()
    }


}