package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.gourmet_inventory_mobile.R
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
sealed class ComandaDelecaoState {
    object Idle : ComandaDelecaoState()
    object Loading : ComandaDelecaoState()
    object Success : ComandaDelecaoState()
    data class Error(val message: String) : ComandaDelecaoState()
}
sealed class ComandaAtualizacaoState {
    object Idle : ComandaAtualizacaoState()
    object Loading : ComandaAtualizacaoState()
    data class Success(val comanda: Comanda) : ComandaAtualizacaoState()
    data class Error(val message: String) : ComandaAtualizacaoState()
}

class ComandaViewModel(private val comandaRepository: ComandaRepository) : ViewModel() {

    //    private val _listaPratosComanda = mutableListOf<Prato>()
    private val _listaPratosComanda = MutableStateFlow<List<Prato>>(emptyList())

    private val _comandaCriacaoState = MutableStateFlow<ComandaCriacaoSate>(ComandaCriacaoSate.Idle)
    val comandaCriacaoState: StateFlow<ComandaCriacaoSate> = _comandaCriacaoState

    private val _deletarComandaState = MutableStateFlow<ComandaDelecaoState>(ComandaDelecaoState.Idle)
    val deletarComandaState: StateFlow<ComandaDelecaoState> = _deletarComandaState

    private val _atualizarComandaState = MutableStateFlow<ComandaAtualizacaoState>(ComandaAtualizacaoState.Idle)
    val atualizarComandaState: StateFlow<ComandaAtualizacaoState> = _atualizarComandaState

    private val _comandaAtual = MutableStateFlow(
        Comanda(
            id = null,
            mesa = "",
            itens = emptyList(),
            status = "Aberta",
            total = 0.0,
            titulo = "",
            idGarcom = 1
        )
    )
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

    fun limparListaPratosComanda() {
        _listaPratosComanda.value = emptyList()
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
    fun createComanda(context: Context, navController: androidx.navigation.NavController) {
        _comandaCriacaoState.value = ComandaCriacaoSate.Loading

        Log.d("ComandaViewModel", "Comanda atual: ${_comandaAtual.value}")
        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            val idEmpresa = user?.id
            Log.d("ComandaViewModel", "User ID: $user")

            val comandaRequisicao = user?.let {
                Comanda(
                    id = null,
                    mesa = _comandaAtual.value?.mesa ?: "",
                    itens = _listaPratosComanda.value,
                    status = "Enviada",
                    total = 0.0,
                    titulo = _comandaAtual.value?.titulo ?: "",
                    idGarcom = it.id
                )
            }

            try {
//                val response = comandaRepository.createComanda(comanda)
                val response = comandaRequisicao?.let { comandaRepository.createComanda(it) }
                response?.body()?.let {
//                    data.clear()
//                    data.add(it)
//                    Log.d("ComandaViewModel", "Data loaded: $data")
                    _comandaCriacaoState.value = ComandaCriacaoSate.Success(it)
                    Log.d("ComandaViewModel", "Comanda criada com sucesso: $it")
                    limparListaPratosComanda()
                } ?: run {
                    Log.e("ComandaViewModel", "Response body is null")
                    _comandaCriacaoState.value = ComandaCriacaoSate.Error("Response body is null")
                }
            } catch (e: Exception) {
                Log.e("ComandaViewModel", "Error loading comandas: ${e.message}")
                _comandaCriacaoState.value = ComandaCriacaoSate.Error(e.message ?: "Unknown error")
            }
            finally {
                navController.navigate("comandaList")
                _comandaCriacaoState.value = ComandaCriacaoSate.Idle
            }
        }
    }

    fun deleteComanda(id: Long) {
        _deletarComandaState.value = ComandaDelecaoState.Loading
        viewModelScope.launch {
            try {
                val response = comandaRepository.deleteComanda(id)

                if (response.isSuccessful) {
                    _deletarComandaState.value = ComandaDelecaoState.Success
                    Log.d("ComandaViewModel", "Comanda com id: ${id} deletada com sucesso")
                } else {
                    // Se a resposta não for bem-sucedida, atualiza o estado para Error
                    _deletarComandaState.value = ComandaDelecaoState.Error("Erro ao deletar comanda")
                }
            } catch (e: Exception) {
                // Se ocorrer uma exceção, atualiza o estado para Error
                Log.e("ComandaViewModel", "Erro ao deletar comanda: ${e.message}")
                _deletarComandaState.value = ComandaDelecaoState.Error("Erro ao deletar comanda")
            } finally {
                // Atualiza o estado para Idle
                _deletarComandaState.value = ComandaDelecaoState.Idle
            }
        }
    }

    fun updateComandaStatus(id: Long, status: String, context: Context) {
        _atualizarComandaState.value = ComandaAtualizacaoState.Loading
        if (status == "Finalizada") {
            Toast.makeText(
                context,
                "Comanda já finalizada",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val resouce = context.resources
        val statusAtt =
            when (status) {
                resouce.getString(R.string.comandaStatus0) -> resouce.getString(
                    R.string.comandaStatus1
                )

                resouce.getString(R.string.comandaStatus1) -> resouce.getString(
                    R.string.comandaStatus2
                )

                resouce.getString(R.string.comandaStatus2) -> resouce.getString(
                    R.string.comandaStatus3
                )

                else -> {
                    resouce.getString(R.string.comandaStatus0)
                }
            }
        Log.d("ComandaViewModel", "Comanda atualizada: ${statusAtt}")

        viewModelScope.launch {
            try {
                val response = comandaRepository.updateComandaStatus(id, statusAtt)

                if (response.isSuccessful) {
                    _atualizarComandaState.value = ComandaAtualizacaoState.Success(response.body()!!)
                    Log.d("ComandaViewModel", "Comanda com id: ${id} atualizada com sucesso")
                    Log.d("ComandaViewModel", "Comanda atualizada: ${response.body()}")
                } else {
                    _atualizarComandaState.value = ComandaAtualizacaoState.Error("Erro ao atualizar comanda")
                    Log.e("ComandaViewModel", "Erro ao atualizar comanda")
                }
            } catch (e: Exception) {
                _atualizarComandaState.value = ComandaAtualizacaoState.Error("Erro ao atualizar comanda")
                Log.e("ComandaViewModel", "Erro ao atualizar comanda: ${e.message}")
            } finally {
                _atualizarComandaState.value = ComandaAtualizacaoState.Idle
            }
        }
    }
}
