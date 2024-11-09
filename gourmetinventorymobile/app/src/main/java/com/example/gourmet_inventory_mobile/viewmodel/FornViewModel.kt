package com.example.gourmet_inventory_mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.repository.Fornecedor.FornecedorRepository
import kotlinx.coroutines.launch

class FornViewModel(private val fornecedorRepository: FornecedorRepository): ViewModel() {

    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<Fornecedor>()
        private set

    init {
        getFornecedores()
    }

    fun getFornecedores() {
        viewModelScope.launch {
            isLoading = true

            val response = fornecedorRepository.obterFornecedores()

            if (response.isSuccessful){
//                data.addAll(response.body()!!)
                data.addAll(response.body() ?: emptyList())
            }
            isLoading = false
        }
    }

}
