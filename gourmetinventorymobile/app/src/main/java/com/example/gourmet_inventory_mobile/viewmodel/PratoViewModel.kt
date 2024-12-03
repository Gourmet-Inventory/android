package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.repository.estoque.PratoRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PratoViewModel(private val pratoRepository: PratoRepository): ViewModel() {
    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<Prato>()
        private set

    fun getPratos(context: Context) {

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()
            val idEmpresa = user?.empresa?.idEmpresa

            isLoading = true

            try {
                val response = idEmpresa?.let {
                    pratoRepository.getPratos(it)
                }
                Log.d("PratoViewModel", "Response: $response")
                Log.d("PratoViewModel", "Response body: ${response?.body()}")

                if (response != null) {
                    if (response.code() == 204) {
                        data.removeAll(data)
                        Log.d("PratoViewModel", "Response é nulo")
                        isLoading = false

                    } else{
                        if (response.isSuccessful && response.body() != null) {
                            Log.d("PratoViewModel", "Response deu certo!")
                            data.removeAll(data)
                            data.addAll(response.body()!!)
                            Log.d("PratoViewModel", "data: $data")
                            isLoading = false
                        }
                    }
                }

            }catch (e: Exception){
                Log.d("PratoViewModel", "Erro ao obter lista de compras: ${e.message}")
                isLoading = false
            }

        }
    }
}