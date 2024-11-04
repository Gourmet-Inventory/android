package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ComandaViewModel(private val comandaRepository: ComandaRepository): ViewModel() {
    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<Comanda>()
        private set


    init {
        getComandas()
    }

    fun getComandas() {

        viewModelScope.launch {
            isLoading = true

            try {
                val response = comandaRepository.getAllComandas()

                Log.d("ComandaViewModel", "Response: $response")
                Log.d("ComandaViewModel", "Response body: ${response?.body()}")

                if (response == null) {
                    Log.d("ComandaViewModel", "Response é nulo")

                } else{
                    if (response.isSuccessful && response.body() != null) {

                        Log.d("ComandaViewModel", "Response deu certo!")
                        data.addAll(response.body() ?: emptyList())
                        Log.d("ComandaViewModel", "data: $data")
                    }
                }

            }catch (e: Exception){
                Log.d("ComandaViewModel", "Erro ao obter lista de comandas: ${e.message}")
            }

        }
    }
}