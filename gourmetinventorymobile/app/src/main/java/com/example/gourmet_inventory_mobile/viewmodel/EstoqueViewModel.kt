package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
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

sealed class EstoqueState {
    object Idle : EstoqueState()
    object Loading : EstoqueState()
    data class Success(val estoqueConsulta: EstoqueConsulta) : EstoqueState()
    data class Error(val message: String) : EstoqueState()
}

class EstoqueViewModel(private val estoqueRepository: EstoqueRepository) : ViewModel() {
    private val _estoqueState = MutableStateFlow<EstoqueState>(EstoqueState.Idle)
    val estoqueState: StateFlow<EstoqueState> = _estoqueState

    fun cadastrarEstoque(context: Context, estoqueCriacao: EstoqueCriacao) {
        _estoqueState.value = EstoqueState.Loading

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
                    _estoqueState.value = EstoqueState.Error("Erro ao cadastrar estoque")
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    Log.d("EstoqueViewModel", "Response é bem sucedido e corpo da resposta não é nulo")
                    _estoqueState.value = EstoqueState.Success(response.body()!!)
                } else {
                    Log.d("EstoqueViewModel", "Response não é bem sucedido ou corpo da resposta é nulo")
                    _estoqueState.value = EstoqueState.Error("Erro ao cadastrar estoque")
                }
            } catch (e: Exception) {
                Log.d("EstoqueViewModel", "Erro ao cadastrar estoque: ${e.message}")
                _estoqueState.value = EstoqueState.Error("Erro ao cadastrar estoque")
            }
        }
    }

    fun obterListaEstoque(context: Context, idEmpresa: Long) {}
}