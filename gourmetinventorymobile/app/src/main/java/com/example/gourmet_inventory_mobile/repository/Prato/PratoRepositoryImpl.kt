package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.service.PratoService
import retrofit2.Response

class PratoRepositoryImpl(private val servicePrato: PratoService) : PratoRepository {

    override suspend fun getPratos(idEmpresa: Long): Response<List<Prato>> {
        return servicePrato.getPratos(idEmpresa)
    }
}