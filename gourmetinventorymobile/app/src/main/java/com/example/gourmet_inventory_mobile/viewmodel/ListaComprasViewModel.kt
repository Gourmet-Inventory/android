package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import com.example.gourmet_inventory_mobile.repository.ListaCompras.ListaComprasRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class ListaComprasViewModel(private val listaComprasRepository: ListaComprasRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<ItemListaCompras>()
        private set


    fun getListaCompras(context: Context) {

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            val idEmpresa = user?.empresa?.idEmpresa

            isLoading = true

            try {
                val response = idEmpresa?.let {
                    listaComprasRepository.obterListaCompras(it)
                }

                Log.d("ListaComprasViewModel", "Response: $response")
                Log.d("ListaComprasViewModel", "Response body: ${response?.body()}")

                if (response == null) {
                   Log.d("ListaComprasViewModel", "Response é nulo")
                    isLoading = false

                } else{
                    if (response.isSuccessful && response.body() != null) {

                        Log.d("ListaComprasViewModel", "Response deu certo!")
                        data.removeAll(data)
                        data.addAll(response.body()?: emptyList())
                        Log.d("ListaComprasViewModel", "data: $data")
                        isLoading = false
                    }
                }

            }catch (e: Exception){
                Log.d("ListaComprasViewModel", "Erro ao obter lista de compras: ${e.message}")
                isLoading = false
            }
        }
    }
}