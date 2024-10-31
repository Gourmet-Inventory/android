package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class EstoqueCriacaoState {
    object Idle : EstoqueCriacaoState()
    object Loading : EstoqueCriacaoState()
    data class Success(val estoqueConsulta: EstoqueConsulta) : EstoqueCriacaoState()
    data class Error(val message: String) : EstoqueCriacaoState()
}

sealed class EstoqueConsultaState {
    object Idle : EstoqueConsultaState()
    object Loading : EstoqueConsultaState()
    data class Success(var estoqueConsulta: List<EstoqueConsulta>) : EstoqueConsultaState()
    data class Error(val message: String) : EstoqueConsultaState()
}

class EstoqueViewModel(private val estoqueRepository: EstoqueRepository) : ViewModel() {
    private val _estoqueConsultaState = MutableStateFlow<EstoqueConsultaState>(EstoqueConsultaState.Idle)
    val estoqueConsultaState: StateFlow<EstoqueConsultaState> = _estoqueConsultaState

    private val _estoqueCriacaoState = MutableStateFlow<EstoqueCriacaoState>(EstoqueCriacaoState.Idle)
    val estoqueCriacaoState: StateFlow<EstoqueCriacaoState> = _estoqueCriacaoState
    var data = mutableStateListOf<EstoqueConsulta>()
        private set

    fun cadastrarEstoque(context: Context, estoqueCriacao: EstoqueCriacao) {
        _estoqueCriacaoState.value = EstoqueCriacaoState.Loading

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            Log.d("EstoqueViewModel", "EstoqueCriacao: $estoqueCriacao")
            val idEmpresa = user?.empresa?.idEmpresa
            Log.d("EstoqueViewModel", "ID Empresa: $idEmpresa")

            try {
                val response = idEmpresa?.let {
                    estoqueRepository.createEstoque(it, estoqueCriacao)
                }

                if (response == null) {
                    Log.d("EstoqueViewModel", "Response é nulo")
                    _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao cadastrar estoque")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    Log.d(
                        "EstoqueViewModel",
                        "Response é bem sucedido e corpo da resposta não é nulo"
                    )
                    _estoqueCriacaoState.value = EstoqueCriacaoState.Success(response.body()!!)
                } else {
                    Log.d(
                        "EstoqueViewModel",
                        "Response não é bem sucedido ou corpo da resposta é nulo"
                    )
                    _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao cadastrar estoque")
                }
            } catch (e: Exception) {
                Log.d("EstoqueViewModel", "Erro ao cadastrar estoque: ${e.message}")
                _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao cadastrar estoque")
            }
        }
    }

    fun obterListaEstoque(context: Context) {
        _estoqueConsultaState.value = EstoqueConsultaState.Loading

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            val idEmpresa = user?.empresa?.idEmpresa

            try {
                val response = idEmpresa?.let {
                    estoqueRepository.getEstoque(it)
                }
                Log.d("EstoqueViewModel", "Response: $response")
                Log.d("EstoqueViewModel", "Response body: ${response?.body()}")

                if (response == null) {
                    _estoqueConsultaState.value = EstoqueConsultaState.Error("Erro ao obter estoque")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    _estoqueConsultaState.value = EstoqueConsultaState.Success(response.body()!!)
                    Log.d("EstoqueViewModel", "_estoqueConsultaState.value: ${_estoqueConsultaState.value}")
                    data.addAll(response.body()!!)
                    Log.d("EstoqueViewModel", "data: $data")
                } else {
                    _estoqueConsultaState.value = EstoqueConsultaState.Error("Erro ao obter estoque")
                }
            } catch (e: Exception) {
                _estoqueConsultaState.value = EstoqueConsultaState.Error("Erro ao obter estoque")
            }
        }
    }

//    fun getEstoque() {
//        viewModelScope.launch {
//            isLoading = true
//
//            val response = estoqueRepository.obterEstoque()
//
//            if (response.isSuccessful) {
////                data.addAll(response.body()!!)
//                data.addAll(response.body() ?: emptyList())
//            }
//            isLoading = false
//        }
//    }
}