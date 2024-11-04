package com.example.gourmet_inventory_mobile.viewmodel

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
import kotlinx.coroutines.launch

class ComandaViewModel(private val comandaRepository: ComandaRepository) : ViewModel() {

    private val _listaPratosComanda = mutableListOf<Prato>()
    val listaPratosComanda: List<Prato> get() = _listaPratosComanda // Expondo como uma lista imutável

    // Função para adicionar um prato
    fun adicionarPrato(prato: Prato) {
        _listaPratosComanda.add(prato)
    }

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
                response?.body()?.let {
                    data.clear()
                    data.addAll(it)
                    Log.d("ComandaViewModel", "Data loaded: $data")
                } ?: Log.e("ComandaViewModel", "Response body is null")
            } catch (e: Exception) {
                Log.e("ComandaViewModel", "Error loading comandas: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun removerPedido(pedido: Prato) {
        _listaPratosComanda.remove(pedido)
    }
}
