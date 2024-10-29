package com.example.gourmet_inventory_mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.repository.Estoque.EstoqueRepository
import kotlinx.coroutines.launch

class EstoqueViewModel(private val estoqueRepository: EstoqueRepository): ViewModel() {

    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<EstoqueConsulta>()
        private set


    init {
        getEstoque()
    }


    fun getEstoque() {
        viewModelScope.launch {
            isLoading = true

            val response = estoqueRepository.obterEstoque()

            if (response.isSuccessful){
//                data.addAll(response.body()!!)
                data.addAll(response.body() ?: emptyList())
            }
            isLoading = false
        }
    }

}
