package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class ComandaCriacaoSate {
    object Idle : ComandaCriacaoSate()
    object Loading : ComandaCriacaoSate()
    data class Success(val comanda: Comanda) : ComandaCriacaoSate()
    data class Error(val message: String) : ComandaCriacaoSate()
}

class ComandaViewModel(private val comandaRepository: ComandaRepository) : ViewModel() {

    //    private val _listaPratosComanda = mutableListOf<Prato>()
    private val _listaPratosComanda = MutableStateFlow<List<Prato>>(emptyList())
    private val _comandaCriacaoState = MutableStateFlow<ComandaCriacaoSate>(ComandaCriacaoSate.Idle)
    val comandaCriacaoState: StateFlow<ComandaCriacaoSate> = _comandaCriacaoState

    private val _comandaAtual = MutableStateFlow(Comanda(mesa = "", itens = emptyList(), status = "Aberta", total = 0.0, titulo = "", idGarcom = 1))
    var comandaAtual: StateFlow<Comanda> = _comandaAtual.asStateFlow()

    val listaPratosComanda: StateFlow<List<Prato>> = _listaPratosComanda

    // Função para adicionar um prato
    fun adicionarPrato(prato: Prato) {
        _listaPratosComanda.value = _listaPratosComanda.value + prato
        Log.d("ComandaViewModel", "Lista de pratos atualizada: ${_listaPratosComanda.value}")
    }

    // Função para remover um prato
    fun removerPrato(prato: Prato) {
        _listaPratosComanda.value = _listaPratosComanda.value - prato
    }

    fun limparComanda() {
        _listaPratosComanda.value = emptyList()
    }

    var isLoading by mutableStateOf(false)
    var data = mutableStateListOf<Comanda>()
        private set

    fun updateComandaAtualMesa(mesa: String) {
        _comandaAtual.value = _comandaAtual.value.copy(mesa = mesa) // Update existing Comanda
        Log.d("ComandaViewModel", "Comanda atualizada: ${_comandaAtual.value}")
    }

    fun updateComandaAtualTitulo(titulo: String) {
        _comandaAtual.value = _comandaAtual.value.copy(titulo = titulo)
        Log.d("ComandaViewModel", "Comanda atualizada: ${_comandaAtual.value}")
    }

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

//    fun getLastComanda() {
//        viewModelScope.launch {
//            isLoading = true
//            try {
//                val response = comandaRepository.getLastComanda()
//                response?.body()?.let {
//                    data.clear()
//                    data.add(it)
//                    Log.d("ComandaViewModel", "Data loaded: $data")
//                } ?: Log.e("ComandaViewModel", "Response body is null")
//            } catch (e: Exception) {
//                Log.e("ComandaViewModel", "Error loading comandas: ${e.message}")
//            } finally {
//                isLoading = false
//            }
//        }
//    }

    //    fun createComanda(comanda: Comanda) {
    fun createComanda(context: Context) {

        Log.d("ComandaViewModel", "Comanda atual: ${_comandaAtual.value}")

        val comandaRequisicao = Comanda(
            mesa = _comandaAtual.value?.mesa ?: "",
            itens = _listaPratosComanda.value,
            status = "Aberta",
            total = 0.0,
            titulo = _comandaAtual.value?.titulo ?: "",
            idGarcom = 1
        )

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            val idEmpresa = user?.id

            try {
//                val response = comandaRepository.createComanda(comanda)
                val response = comandaRepository.createComanda(comandaRequisicao)
                response?.body()?.let {
//                    data.clear()
//                    data.add(it)
//                    Log.d("ComandaViewModel", "Data loaded: $data")
                    _comandaCriacaoState.value = ComandaCriacaoSate.Success(it)
                    Log.d("ComandaViewModel", "Comanda criada com sucesso: $it")
                } ?: run {
                    Log.e("ComandaViewModel", "Response body is null")
                    _comandaCriacaoState.value = ComandaCriacaoSate.Error("Response body is null")
                }
            } catch (e: Exception) {
                Log.e("ComandaViewModel", "Error loading comandas: ${e.message}")
                _comandaCriacaoState.value = ComandaCriacaoSate.Error(e.message ?: "Unknown error")
            } finally {
                isLoading = false
                _comandaCriacaoState.value = ComandaCriacaoSate.Idle
            }
        }
    }
}
