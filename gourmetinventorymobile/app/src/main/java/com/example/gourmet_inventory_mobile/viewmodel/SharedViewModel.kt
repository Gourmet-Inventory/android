import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.model.Medidas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class SharedViewModel constructor() : ViewModel() {

    private val _estoque = MutableStateFlow(
        EstoqueCriacao(
            lote = "",
            manipulado = false,
            nome = "",
            categoria = "",
            tipoMedida = Medidas.UNIDADE,
            unitario = 1,
            valorMedida = 1.0,
            localArmazenamento = "Local",
            dtaCadastro = LocalDate.now(),
            dtaAviso = LocalDate.now().plusDays(1),
            marca = ""
        )
    )

    val estoque: StateFlow<EstoqueCriacao> = _estoque

    fun atualizarEstoque(novoEstoque: EstoqueCriacao) {
        _estoque.update { novoEstoque.copy() }
        Log.d("SharedViewModel", "Estoque atualizado: $novoEstoque")
    }
}
