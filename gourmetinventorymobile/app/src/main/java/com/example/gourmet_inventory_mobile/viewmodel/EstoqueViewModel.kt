package com.example.gourmet_inventory_mobile.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepository
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

// Sealed class para representar os estados de requisição de um estoque
sealed class EstoqueCriacaoState {
    object Idle : EstoqueCriacaoState()
    object Loading : EstoqueCriacaoState()
    data class Success(val estoqueConsulta: EstoqueConsulta) : EstoqueCriacaoState()
    data class Error(val message: String) : EstoqueCriacaoState()
}
sealed class EstoqueConsultaState {
    object Idle : EstoqueConsultaState()
    object Loading : EstoqueConsultaState()
    data class Success(val estoqueConsulta: List<EstoqueItemDiscriminator>) : EstoqueConsultaState()
    data class Error(val message: String) : EstoqueConsultaState()
}
sealed class EstoqueDelecaoState {
    object Idle : EstoqueDelecaoState()
    object Loading : EstoqueDelecaoState()
    object Success : EstoqueDelecaoState()
    data class Error(val message: String) : EstoqueDelecaoState()
}

fun parseEstoqueItem(data: Map<String, Any>): EstoqueItemDiscriminator {
    Log.d("parseEstoqueItem", "Data recebida: $data")

    val manipulado = data["manipulado"] as? Boolean ?: throw IllegalArgumentException("Campo 'manipulado' está ausente ou inválido.")
    return when (manipulado) {
        true -> {
            Log.d("parseEstoqueItem", "Processando item manipulado.")
            (data["receita"] as? ReceitaConsultaDto)?.let {
                EstoqueItemDiscriminator.Manipulado(
                    idItem = (data["idItem"] as? Double)?.toLong() ?: throw IllegalArgumentException("Campo 'idItem' ausente ou inválido."),
                    manipulado = manipulado,
                    lote = data["lote"] as? String ?: throw IllegalArgumentException("Campo 'lote' ausente ou inválido."),
                    nome = data["nome"] as? String ?: throw IllegalArgumentException("Campo 'nome' ausente ou inválido."),
                    categoria = data["categoria"] as? String ?: throw IllegalArgumentException("Campo 'categoria' ausente ou inválido."),
                    tipoMedida = data["tipoMedida"] as? String ?: throw IllegalArgumentException("Campo 'tipoMedida' ausente ou inválido."),
                    unitario = (data["unitario"] as? Double)?.toInt() ?: 0,
                    valorMedida = data["valorMedida"] as? Double ?: 0.0,
                    valorTotal = data["valorTotal"] as? Double ?: 0.0,
                    localArmazenamento = data["localArmazenamento"] as? String ?: "",
                    dtaCadastro = LocalDate.parse(data["dtaCadastro"] as? String ?: throw IllegalArgumentException("Campo 'dtaCadastro' ausente ou inválido.")),
                    dtaAviso = LocalDate.parse(data["dtaAviso"] as? String ?: throw IllegalArgumentException("Campo 'dtaAviso' ausente ou inválido.")),
                    descricao = (data["descricao"] as? String)!!,
                    receita = it
                )
            } ?: throw IllegalArgumentException("Campo 'receita' ausente ou inválido.")
        }
        false -> {
            Log.d("parseEstoqueItem", "Processando item não manipulado.")
            EstoqueItemDiscriminator.Industrializado(
                idItem = (data["idItem"] as? Double)?.toLong() ?: throw IllegalArgumentException("Campo 'idItem' ausente ou inválido."),
                manipulado = manipulado,
                lote = data["lote"] as? String ?: throw IllegalArgumentException("Campo 'lote' ausente ou inválido."),
                nome = data["nome"] as? String ?: throw IllegalArgumentException("Campo 'nome' ausente ou inválido."),
                categoria = CategoriaEstoque.valueOf(data["categoria"] as? String ?: throw IllegalArgumentException("Campo 'categoria' ausente ou inválido.")),
                tipoMedida = Medidas.valueOf(data["tipoMedida"] as? String ?: throw IllegalArgumentException("Campo 'tipoMedida' ausente ou inválido.")),
                unitario = (data["unitario"] as? Double)?.toInt() ?: 0,
                valorMedida = data["valorMedida"] as? Double ?: 0.0,
                valorTotal = data["valorTotal"] as? Double ?: 0.0,
                localArmazenamento = data["localArmazenamento"] as? String ?: "",
                dtaCadastro = LocalDate.parse(data["dtaCadastro"] as? String ?: throw IllegalArgumentException("Campo 'dtaCadastro' ausente ou inválido.")),
                dtaAviso = LocalDate.parse(data["dtaAviso"] as? String ?: throw IllegalArgumentException("Campo 'dtaAviso' ausente ou inválido.")),
                marca = data["marca"] as? String ?: ""
            )
        }
        else -> throw IllegalArgumentException("Tipo desconhecido: $manipulado")
    }
}

class EstoqueViewModel(private val estoqueRepository: EstoqueRepository) : ViewModel() {

    private val _estoqueConsultaState = MutableStateFlow<EstoqueConsultaState>(EstoqueConsultaState.Idle)
    val estoqueConsultaState: StateFlow<EstoqueConsultaState> = _estoqueConsultaState
    private val _estoqueCriacaoState = MutableStateFlow<EstoqueCriacaoState>(EstoqueCriacaoState.Idle)
    val estoqueCriacaoState: StateFlow<EstoqueCriacaoState> = _estoqueCriacaoState
    var data = mutableStateListOf<Any>()
        private set
    private val _deletarEstoqueState = MutableStateFlow<EstoqueDelecaoState>(EstoqueDelecaoState.Idle)

    fun cadastrarEstoque(context: Context, estoqueCriacaoDto: EstoqueCriacaoDto) {
        _estoqueCriacaoState.value = EstoqueCriacaoState.Loading

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            Log.d("EstoqueViewModel", "EstoqueCriacao: $estoqueCriacaoDto")
            val idEmpresa = user?.empresa?.idEmpresa
            Log.d("EstoqueViewModel", "ID Empresa: $idEmpresa")

            try {
                val response = idEmpresa?.let {
                    estoqueRepository.createEstoque(it, estoqueCriacaoDto)
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
                        "Response não é bem sucedido ou corpo da resposta é nulo: ${response}"
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
                    Log.d("EstoqueViewModel", "Response é bem sucedido e corpo da resposta não é nulo")
                    val estoqueItens = response.body()?.map { parseEstoqueItem(it) } ?: emptyList()
                    Log.d("EstoqueViewModel", "estoqueItens: $estoqueItens")

                    _estoqueConsultaState.value = EstoqueConsultaState.Success(emptyList())
                    _estoqueConsultaState.value = EstoqueConsultaState.Success(estoqueItens)
                    Log.d("EstoqueViewModel", "_estoqueConsultaState.value: ${_estoqueConsultaState.value}")
                    data.clear()
                    data.addAll(response.body()!!)
                    Log.d("EstoqueViewModel", "data: $data")
                } else {
                    _estoqueConsultaState.value = EstoqueConsultaState.Error("Erro ao obter estoque ${response}")
                }
            } catch (e: Exception) {
                _estoqueConsultaState.value = EstoqueConsultaState.Error("Erro ao obter estoque catch")
            }
        }
    }

    fun atualizarEstoque(context: Context, idEstoque: Long, estoqueCriacaoDto: EstoqueCriacaoDto) {
        _estoqueCriacaoState.value = EstoqueCriacaoState.Loading
        Log.d("EstoqueViewModel", "AtualizarEstoque: $estoqueCriacaoDto")

        viewModelScope.launch {
            val user = DataStoreUtils(context).obterUsuario()?.first()
            Log.d("EstoqueViewModel", "EstoqueCriacao: $estoqueCriacaoDto")
            val idEmpresa = user?.empresa?.idEmpresa
            Log.d("EstoqueViewModel", "ID Empresa: $idEmpresa")

            try {
                val response = idEmpresa?.let {
                    estoqueCriacaoDto.toEstoque(user.empresa).let { estoque ->
                        estoqueRepository.updateEstoque(idEstoque, estoque)
                    }
                }

                if (response == null) {
                    Log.d("EstoqueViewModel", "Response é nulo")
                    _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao atualizar estoque")
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
                    _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao atualizar estoque")
                }
            } catch (e: Exception) {
                Log.d("EstoqueViewModel", "Erro ao atualizar estoque: ${e.message}")
                _estoqueCriacaoState.value = EstoqueCriacaoState.Error("Erro ao atualizar estoque")
            }
        }
    }

    fun deletarEstoque(idItem: Long) {

        viewModelScope.launch {
            try {
                val response = estoqueRepository.deleteEstoque(idItem)

                if (response.isSuccessful) {
                    _deletarEstoqueState.value = EstoqueDelecaoState.Success
                    Log.d("EstoqueViewModel", "Item deletado com sucesso: $idItem")
                } else {
                    // Se a resposta não for bem-sucedida, atualiza o estado para Error
                    _deletarEstoqueState.value = EstoqueDelecaoState.Error("Erro ao deletar estoque")
                }
            } catch (e: Exception) {
                Log.d("EstoqueViewModel", "Erro ao deletar estoque: ${e.message}")
                _deletarEstoqueState.value = EstoqueDelecaoState.Error("Erro ao deletar estoque")
            }
        }
    }
}