import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SharedViewModel constructor() : ViewModel() {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private val _estoque = MutableStateFlow(
        EstoqueCriacao(
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

    val estoque: StateFlow<EstoqueCriacao> = _estoque.asStateFlow()

    fun atualizarEstoque(novoEstoque: EstoqueCriacao) {
        viewModelScope.launch {
            _estoque.emit(
                novoEstoque
            )
        }
//        _estoque.update { novoEstoque.copy() }
        Log.d("SharedViewModel", "Estoque atualizado: $novoEstoque")
    }

    fun criarEstoqueAtualizado(estoque : EstoqueCriacao): EstoqueCriacao? {
        return try {
            EstoqueCriacao(
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
                EstoqueCriacao(
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
