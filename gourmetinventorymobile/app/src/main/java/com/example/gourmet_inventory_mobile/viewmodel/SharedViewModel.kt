import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteConsultaDto
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoAtualizacao
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SharedViewModel constructor() : ViewModel() {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    var receita = mutableStateListOf<IngredienteConsultaDto>()
        private set

    var receitaCriacao = mutableStateListOf<IngredienteCriacaoDto>()
        private set

    var receitaAtualizacaoConsulta = mutableStateListOf<IngredienteConsultaDto>()
        private set

    var receitaAtualizacaoCriacao = mutableStateListOf<IngredienteCriacaoDto>()
        private set

    private val _estoque = MutableStateFlow(
        EstoqueCriacaoDto(
            lote = "",
            manipulado = false,
            nome = "",
            categoria = CategoriaEstoque.OUTROS,
            tipoMedida = Medidas.UNIDADE,
            unitario = 0,
            valorMedida = 0.0,
            localArmazenamento = "",
            dtaCadastro = LocalDate.now(),
            dtaAviso = LocalDate.now().plusDays(1),
            marca = ""
        )
    )
    val estoque: StateFlow<EstoqueCriacaoDto> = _estoque.asStateFlow()

    //Adicona um ingrediente a receita para criação de um estoque manipulado
    fun adicionarIngredienteCriacao(tipoMedida: String, valorMedida: Double, idItem: Long, edicao: Boolean) {
        if (edicao){
            receitaAtualizacaoCriacao.add(
                IngredienteCriacaoDto(
                    idItem = idItem,
                    tipoMedida = Medidas.valueOf(tipoMedida),
                    valorMedida = valorMedida,
                )
            )
        } else {
            receitaCriacao.add(
                IngredienteCriacaoDto(
                    idItem = idItem,
                    tipoMedida = Medidas.valueOf(tipoMedida),
                    valorMedida = valorMedida,
                )
            )
        }
        Log.d("SharedViewModel", "Ingrediente adicionado: $receitaCriacao")
    }

    //Adiciona um ingrediente a receita para a lista de visualização dos ingredientes
    fun adicionarIngredienteConsulta(
        idItem: Long,
        nome: String,
        tipoMedida: String,
        valorMedida: Double
    ) {
        receita.add(
            IngredienteConsultaDto(
                nome = nome,
                tipoMedida = Medidas.valueOf(tipoMedida),
                valorMedida = valorMedida,
            )
        )
        Log.d("SharedViewModel", "Ingrediente adicionado: $receita")
    }

    //Remove um ingrediente da receita para criação de um estoque manipulado
    fun removerIngredienteCriacao(idItem: Long) {
        this.receitaCriacao.removeIf { it.idItem == idItem }
    }

    //Remove um ingrediente da receita de visualização dos ingredientes
    fun removerIngredienteConsulta(receita: IngredienteConsultaDto) {
        this.receita.remove(receita)
    }

    //Adiciona um ingrediente a receita para a lista de visualização dos ingredientes que vão ser atualizados
    fun adicionarIngredienteConsultaAtualizacao(
        idItem: Long,
        nome: String,
        tipoMedida: String,
        valorMedida: Double
    ) {
        if (receitaAtualizacaoConsulta.isEmpty()) {
            receitaAtualizacaoConsulta.addAll(receita)
            receitaAtualizacaoConsulta.add(
                IngredienteConsultaDto(
                    nome = nome,
                    tipoMedida = Medidas.valueOf(tipoMedida),
                    valorMedida = valorMedida,
                )
            )
        } else {
            receitaAtualizacaoConsulta.add(
                IngredienteConsultaDto(
                    nome = nome,
                    tipoMedida = Medidas.valueOf(tipoMedida),
                    valorMedida = valorMedida,
                )
            )
        }
        Log.d("SharedViewModel", "Ingrediente adicionado: $receita")
    }

    //Remover um ingrediente da receita para a lista de visualização dos ingredientes que vão ser atualizados
    fun removerIngredienteConsultaAtualizacao(receitaItem: IngredienteConsultaDto, receitas: List<IngredienteConsultaDto>) {
        if (receitaAtualizacaoConsulta.isEmpty()) {
            receitaAtualizacaoConsulta.addAll(receitas)
        }
        this.receitaAtualizacaoConsulta.remove(receitaItem)
    }

    //Remove um ingrediente da receita de visualização dos ingredientes que vão ser atualizados
    fun removerIngredienteAtualizacaoCriacao(idItem: Long) {
        if (receitaAtualizacaoCriacao.isEmpty()) {
            receitaAtualizacaoCriacao.addAll(receitaCriacao)
        }
        this.receitaAtualizacaoCriacao.removeIf { it.idItem == idItem }
    }

    //Cria um estoque manipulado com a receita de ingredientes para criação
    fun criarEstoqueManipuladoCriacao(estoque: EstoqueCriacaoDto): EstoqueManipuladoCriacao {
        Log.d("SharedViewModel", "Criando EstoqueManipuladoCriacao")
        Log.d("SharedViewModel", "Receita: ${receitaCriacao.size}")
        return EstoqueManipuladoCriacao(
            estoqueIngredienteCriacaoDto = estoque,
            receita = receitaCriacao.toList()
        )
    }

    //Cria um estoque manipulado com a receita de ingredientes para atualização
    fun criarEstoqueManipuladoAtualizacao(estoque: EstoqueIngredienteAtualizacaoDto): EstoqueManipuladoAtualizacao {
        Log.d("SharedViewModel", "Criando EstoqueManipuladoCriacao")
        Log.d("SharedViewModel", "Receita: ${receitaCriacao.size}")
        return EstoqueManipuladoAtualizacao(
            estoqueIngredienteAtualizacaoDto = estoque,
            receita = receitaAtualizacaoCriacao.toList()
        )
    }

    //Mantém o estoque atualizado para a requisição
    fun atualizarEstoque(novoEstoque: EstoqueCriacaoDto) {
        viewModelScope.launch {
            _estoque.emit(
                novoEstoque
            )
        }
//        _estoque.update { novoEstoque.copy() }
        Log.d("SharedViewModel", "Estoque atualizado: $novoEstoque")
    }

    //Cria um estoque atualizado para a requisição
    fun criarEstoqueAtualizado(estoque: EstoqueCriacaoDto): EstoqueCriacaoDto? {
        return try {
            EstoqueCriacaoDto(
                lote = estoque?.lote ?: "",
                manipulado = estoque?.manipulado ?: false,
                nome = estoque?.nome ?: "",
                categoria = estoque?.categoria ?: CategoriaEstoque.OUTROS,
                tipoMedida = estoque?.tipoMedida ?: Medidas.UNIDADE,
                unitario = estoque?.unitario ?: 0,
                valorMedida = estoque?.valorMedida ?: 0.0,
                localArmazenamento = estoque?.localArmazenamento ?: "",
                dtaCadastro = LocalDate.parse(estoque?.dtaCadastro.toString(), dateFormatter),
                dtaAviso = LocalDate.parse(estoque?.dtaAviso.toString(), dateFormatter),
                marca = estoque?.marca ?: ""
            )
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao criar EstoqueCriacao: ${e.message}")
            null
        }
    }

    //Limpa o estoque
    fun limparEstoque() {
        viewModelScope.launch {
            _estoque.emit(
                EstoqueCriacaoDto(
                    lote = "",
                    manipulado = false,
                    nome = "",
                    categoria = CategoriaEstoque.OUTROS,
                    tipoMedida = Medidas.UNIDADE,
                    unitario = 0,
                    valorMedida = 0.0,
                    localArmazenamento = "",
                    dtaCadastro = LocalDate.now(),
                    dtaAviso = LocalDate.now().plusDays(1),
                    marca = ""
                )
            )
//            receitaCriacao.clear()
//            receita.clear()
            receitaAtualizacaoCriacao.clear()
            receitaAtualizacaoConsulta.clear()
        }
    }
}
