package com.example.gourmet_inventory_mobile.repository.ListaCompras

import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import retrofit2.Response

interface ListaComprasRepository {
    suspend fun obterListaCompras(idEmpresa: Long): Response<List<ItemListaCompras>>
}