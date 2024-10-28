package com.example.gourmet_inventory_mobile.repository.Fornecedor

import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.service.FornecedorService
import retrofit2.Response

class FornecedorRepositoryLocalImpl(): FornecedorRepository {
    override suspend fun obterFornecedores(): Response<List<Fornecedor>> {
        return Response.success(
            List(15){
                Fornecedor(
                    idFornecedor =  it.toLong(),
                    nomeFornecedor = "Fornecedor $it",
                    cnpj = "CNPJ $it",
                    cep =  "string",
                    logradouro = "string",
                    complemento= "string",
                    bairro = "string",
                    localidade ="string",
                    uf ="string",
                    numeracaoLogradouro = "string",
                    telefone = "string",
                    categoria= "string"
                )
            }
        )
    }

}