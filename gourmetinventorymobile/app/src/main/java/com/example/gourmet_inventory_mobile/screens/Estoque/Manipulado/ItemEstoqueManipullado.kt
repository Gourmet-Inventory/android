import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteConsultaDto
import com.example.gourmet_inventory_mobile.model.Ingrediente.IngredienteCriacaoDto
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoConsulta

import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ItemEstoqueManipuladoScreen(
    estoqueConsulta: EstoqueItemDiscriminator.Manipulado?,
    onItemEstoqueClick: (String) -> Unit,
    onItemEstoqueViewEditarClick: () -> Unit,
    onItemEstoqueViewExcluirClick: () -> Unit,
) {
//    val receita = listOf(
//        Receita(50.0, "GRAMAS", "TOMATEEEEEEEEEEEE"),
//        Receita(50.0, "GRAMAS", "TOMATE"),
//        Receita(50.0, "GRAMAS", "TOMATE"),
//        Receita(50.0, "GRAMAS", "TOMATE"),
//        Receita(50.0, "GRAMAS", "TOMATE"),
//        Receita(50.0, "GRAMAS", "TOMATE")
//    )

    var lote by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var localArmazenamento by remember { mutableStateOf("") }
    var quantidadeUnitaria by remember { mutableStateOf("") }
    var tipoMedida by remember { mutableStateOf("") }
    var valorMedida by remember { mutableStateOf("") }
    var valorTotal by remember { mutableStateOf("") }
    var dataCadastro by remember { mutableStateOf("") }
    var dataAviso by remember { mutableStateOf("") }
    var receita by remember { mutableStateOf(listOf<IngredienteConsultaDto>()) }

    if (estoqueConsulta != null) {
        lote = estoqueConsulta.lote
        categoria = estoqueConsulta.categoria.toString()
        localArmazenamento = estoqueConsulta.localArmazenamento
        quantidadeUnitaria = estoqueConsulta.unitario.toString()
        tipoMedida = estoqueConsulta.tipoMedida.nomeExibicao
        valorMedida = estoqueConsulta.valorMedida.toString()
        valorTotal = estoqueConsulta.valorTotal.toString()
        dataCadastro = estoqueConsulta.dtaCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        dataAviso = estoqueConsulta.dtaAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        receita = estoqueConsulta.receita.receita
    }


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onItemEstoqueClick("Voltar") },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier
//                            .background(color = White, shape = RoundedCornerShape(50))
                            .fillMaxSize(),
//                            .border(1.dp, Color.Black, shape = RoundedCornerShape(50)),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }
    ) { padding ->
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val context = LocalContext.current

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Molho de Tomate",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                    )
                    Text(
                        text = "MANIPULADO",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 50.dp
                        )
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .height(250.dp)
                ) {
                    item { InfoItemManipulado("Lote:", lote) }
                    item { InfoItemManipulado("Categoria:", categoria, topPadding = 22.dp) }
                    item {
                        InfoItemManipulado(
                            "Local Armazenamento:",
                            localArmazenamento,
                            topPadding = 22.dp
                        )
                    }
                    item {
                        InfoItemManipulado(
                            "Quantidade Unitária:",
                            quantidadeUnitaria,
                            topPadding = 22.dp
                        )
                    }
                    item { InfoItemManipulado("Tipo Medida:", tipoMedida, topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Medida:", valorMedida, topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Total:", valorTotal, topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Cadastro:", dataCadastro, topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Aviso:", dataAviso, topPadding = 22.dp) }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Receita:", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                        .height(170.dp)
                        .border(1.dp, color = Color.Black)
                ) {
                    ItensReceita(receitas = receita)
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onItemEstoqueViewEditarClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Verde,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Editar")
                    }
                    Button(
                        onClick = {
                            onItemEstoqueViewExcluirClick()
                            Toast.makeText(
                                context,
                                "Item Excluído com sucesso",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFED5656),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(start = 5.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                            contentDescription = "Excluir",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Excluir")
                    }
                }

            }
        }
    }
}

@Composable
fun InfoItemManipulado(label: String, value: String, topPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)

    }


}

@Composable
fun ItensReceita(receitas: List<IngredienteConsultaDto>) {
    DrawScrollableView(
        modifier = Modifier
            .fillMaxHeight(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 3.dp, bottom = 3.dp, end = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                receitas.forEach() { receitaItem ->
                    ItemReceita(receita = receitaItem)
                }
            }
        }
    )
}


@Composable
fun ItemReceita(receita: IngredienteConsultaDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${receita.valorMedida} ${receita.tipoMedida.nomeExibicao} - ${receita.nome}",
            fontSize = 19.sp,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.horizontalScroll(rememberScrollState())
        )
    }
}

@Composable
fun ItensReceitaCadastro(receitas: List<IngredienteConsultaDto>, sharedViewModel: SharedViewModel) {
    DrawScrollableView(
        modifier = Modifier
            .fillMaxHeight(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 3.dp, bottom = 3.dp, end = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                receitas.forEach() { receitaItem ->
                    ItemReceitaCriacao(receita = receitaItem, sharedViewModel = sharedViewModel)
                }
            }
        }
    )
}

@Composable
fun ItemReceitaCriacao(receita: IngredienteConsultaDto, sharedViewModel: SharedViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${receita.valorMedida} ${receita.tipoMedida.abreviacao} - ${receita.nome}",
            fontSize = 19.sp,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.horizontalScroll(rememberScrollState())
        )
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Remover",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    sharedViewModel.removerIngredienteConsulta(receita)
                    val index = sharedViewModel.receita.indexOf(receita)
                    val idItem = sharedViewModel.receitaCriacao.getOrNull(index)?.idItem ?: 0L
                    sharedViewModel.removerIngredienteCriacao(idItem)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemManipuladoPreview() {
    val ingredienteConsultaDto = IngredienteConsultaDto("Ingrediente 1", Medidas.UNIDADE, 50.0)

    ItemEstoqueManipuladoScreen(
        onItemEstoqueClick = {},
        onItemEstoqueViewEditarClick = {},
        onItemEstoqueViewExcluirClick = {},
        estoqueConsulta = EstoqueItemDiscriminator.Manipulado(
            idItem = 1,
            manipulado = true,
            nome = "Molho de Tomate",
            lote = "123",
            categoria = CategoriaEstoque.OUTROS,
            tipoMedida = Medidas.UNIDADE,
            unitario = 1,
            valorMedida = 50.0,
            valorTotal = 50.0,
            localArmazenamento = "Local",
            dtaCadastro = LocalDate.now(),
            dtaAviso = LocalDate.now(),
            descricao = "Descrição",
            receita = ReceitaConsultaDto(
                1, listOf(ingredienteConsultaDto)
            )
        )
    )
}

