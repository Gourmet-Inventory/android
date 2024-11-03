package com.example.gourmet_inventory_mobile.repository.ListaCompras

import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import com.example.gourmet_inventory_mobile.service.FornecedorService
import com.example.gourmet_inventory_mobile.service.ListaComprasService
import retrofit2.Response

class ListaComprasRepositoryImpl(private val serviceListaCompras: ListaComprasService) : ListaComprasRepository {

    override suspend fun obterListaCompras(idEmpresa: Long): Response<List<ItemListaCompras>> {
        return serviceListaCompras.getListaCompras(idEmpresa)
    }


}