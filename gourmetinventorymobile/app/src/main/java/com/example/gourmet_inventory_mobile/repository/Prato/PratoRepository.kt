package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Prato
import retrofit2.Response

interface PratoRepository {
    suspend fun getPratos(idEmpresa: Long) : Response<List<Prato>>

}