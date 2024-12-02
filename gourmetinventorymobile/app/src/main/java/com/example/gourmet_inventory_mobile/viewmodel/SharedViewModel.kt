import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteConsultaDto
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.Medidas
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

//    val estoqueManipuladoCriacao = EstoqueManipuladoCriacao(
//        estoqueIngredienteCriacaoDto = estoque.value,
//        receita = receitaCriacao
//    )

    fun adicionarIngredienteCriacao(tipoMedida: String, valorMedida: Double, idItem: Long) {
        receitaCriacao.add(
            IngredienteCriacaoDto(
                idItem = idItem,
                tipoMedida = Medidas.valueOf(tipoMedida),
                valorMedida = valorMedida,
            )
        )
        Log.d("SharedViewModel", "Ingrediente adicionado: $receitaCriacao")
    }

    fun removerIngredienteCriacao(idItem: Long) {
        this.receitaCriacao.removeIf { it.idItem == idItem }
    }

    fun adicionarIngredienteConsulta(idItem: Long, nome: String, tipoMedida: String, valorMedida: Double) {
        receita.add(
            IngredienteConsultaDto(
                nome = nome,
                tipoMedida = Medidas.valueOf(tipoMedida),
                valorMedida = valorMedida,
            )
        )
        Log.d("SharedViewModel", "Ingrediente adicionado: $receita")
    }

    fun removerIngredienteConsulta(receita: IngredienteConsultaDto) {
        this.receita.remove(receita)
    }

    fun criarEstoqueManipuladoCriacao(estoque: EstoqueCriacaoDto): EstoqueManipuladoCriacao {
        Log.d("SharedViewModel", "Criando EstoqueManipuladoCriacao")
        Log.d("SharedViewModel", "Receita: ${receitaCriacao.size}")
        return EstoqueManipuladoCriacao(
            estoqueIngredienteCriacaoDto = estoque,
            receita = receitaCriacao.toList()
        )
    }

    fun atualizarEstoque(novoEstoque: EstoqueCriacaoDto) {
        viewModelScope.launch {
            _estoque.emit(
                novoEstoque
            )
        }
//        _estoque.update { novoEstoque.copy() }
        Log.d("SharedViewModel", "Estoque atualizado: $novoEstoque")
    }

    fun criarEstoqueAtualizado(estoque : EstoqueCriacaoDto): EstoqueCriacaoDto? {
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
                dtaAviso = LocalDate.parse(estoque?.dtaAviso.toString() , dateFormatter),
                marca = estoque?.marca ?: ""
            )
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Erro ao criar EstoqueCriacao: ${e.message}")
            null
        }
    }

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
        }
    }
}
