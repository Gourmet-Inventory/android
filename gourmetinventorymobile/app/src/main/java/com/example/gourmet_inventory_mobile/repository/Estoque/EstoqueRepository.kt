package com.example.gourmet_inventory_mobile.repository.Estoque

import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import retrofit2.Response

interface EstoqueRepository {
    suspend fun obterEstoque(): Response<List<EstoqueConsulta>>
}