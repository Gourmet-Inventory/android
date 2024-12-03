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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class ItemDelecaoState {
    object Idle : ItemDelecaoState()
    object Loading : ItemDelecaoState()
    object Success : ItemDelecaoState()
    data class Error(val message: String) : ItemDelecaoState()
}

class ListaComprasViewModel(private val listaComprasRepository: ListaComprasRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)

    var data = mutableStateListOf<ItemListaCompras>()
        private set

    private val _deletarItemComprasState = MutableStateFlow<ItemDelecaoState>(ItemDelecaoState.Idle)

    val deletarItemComprasState: StateFlow<ItemDelecaoState> = _deletarItemComprasState

    fun getListaCompras(context: Context) {

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()
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

    fun setItemDelecaoState(state: ItemDelecaoState) {
        _deletarItemComprasState.value = state
    }

    fun deletarItemListaCompras(idItem: Long) {

        viewModelScope.launch {
            try {
                val response = listaComprasRepository.deleteItemListaCompras(idItem)

                if (response.isSuccessful) {
                    _deletarItemComprasState.value = ItemDelecaoState.Success
                    Log.d("ListaComprasViewModel", "Item deletado com sucesso: $idItem")

                } else {
                    // Se a resposta não for bem-sucedida, atualiza o estado para Error
                    _deletarItemComprasState.value = ItemDelecaoState.Error("Erro ao deletar item")
                }
            } catch (e: Exception) {
                Log.d("ListaComprasViewModel", "Erro ao deletar estoque: ${e.message}")
                _deletarItemComprasState.value = ItemDelecaoState.Error("Erro ao deletar item")
            }
        }
    }
}