package com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado

import SharedViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Receita.ReceitaConsultaDto
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepositoryImplLocal
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.CategoriaEstoqueSelectBox
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.LocalArmazenamentoSelectBoxCadastrar
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueCriacaoState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import java.time.LocalDate

@Composable
fun EdicaoItemManipulavelScreen(
    estoqueViewModel: EstoqueViewModel,
    estoque: EstoqueItemDiscriminator.Manipulado,
    sharedViewModel: SharedViewModel,
    onEdicaoItemManipuladoVoltarClick: () -> Unit = {},
    onEdicaoItemManipuladoProximoClick: () -> Unit = {}
) {
    val estoque by sharedViewModel.estoque.collectAsState()

    val estoqueEdicao by sharedViewModel.estoque.collectAsState()

    val estoqueState by estoqueViewModel.estoqueCriacaoState.collectAsState()

    //Variaveis de edição
    val nomeEntada = if (estoqueEdicao.nome == "") estoque.nome else estoqueEdicao.nome
    var nome by remember { mutableStateOf(nomeEntada) }
    val loteEntrada = if (estoqueEdicao.lote.isBlank()) estoque.lote else estoqueEdicao.lote
    var lote by remember { mutableStateOf(loteEntrada) }
    val categoriaEntrada =
        if (estoqueEdicao.categoria == CategoriaEstoque.OUTROS) estoque.categoria else estoqueEdicao.categoria
    var categoria by remember { mutableStateOf(categoriaEntrada) }
    val localArmazenamentoEntrada =
        if (estoqueEdicao.localArmazenamento.isBlank()) estoque.localArmazenamento else estoqueEdicao.localArmazenamento
    var localArmazenamento by remember {
        mutableStateOf(localArmazenamentoEntrada)
    }
    var marcaEntrada = if (estoqueEdicao.marca == "") estoque.marca else estoqueEdicao.marca
    var marca by remember { mutableStateOf(estoque.marca) }
    val selectedCategoryEntrada =
        if (estoqueEdicao.categoria == null) CategoriaEstoque.OUTROS.nomeExibicao else estoqueEdicao.categoria
    var selectedCategory by remember { mutableStateOf(selectedCategoryEntrada) }

    //Variavel de exibição de componente
    val formularioExibicao by remember { mutableStateOf("Formulario1") }

    //Variáveis de erro
    var valorMedidaErro by remember { mutableStateOf(false) }
    var dataCadastroErro by remember { mutableStateOf(false) }
    var dataAvisoErro by remember { mutableStateOf(false) }
    var dataAvisoAnteriorErro by remember { mutableStateOf(false) }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            sharedViewModel.limparEstoque()
                            onEdicaoItemManipuladoVoltarClick()
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Voltar",
                                Modifier.size(44.dp),
                                tint = Color.Black
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp),
                        //                    .padding(top = 45.dp, start = 26.dp, end = 26.dp),
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Editar Item",
                            modifier = Modifier,
                            color = Black,
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 35.sp, fontFamily = JostBold)
                        )
                    }
                    Text(
                        text = "MANIPULAVEL",
                        modifier = Modifier,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )

                    if (formularioExibicao == "Formulario1") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
//                            .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            InputEdicaoManipulavel(titulo = "Nome", valorCampo = nome,
                                mudaValor = { novoValor ->
                                    nome = novoValor
                                })

                            InputEdicaoManipulavel(titulo = "Lote", valorCampo = lote,
                                mudaValor = { novoValor ->
                                    lote = novoValor
                                })

                            CategoriaEstoqueSelectBox(
                                selectedOption = CategoriaEstoque.valueOf(selectedCategory.toString()),
                                onCategoriaEstoqueChange = { newCategory ->
                                    selectedCategory = CategoriaEstoque.valueOf(newCategory)
                                }
                            )

                            marca?.let {
                                InputEdicaoManipulavel(titulo = "Marca", valorCampo = it,
                                    mudaValor = { novoValor ->
                                        marca = novoValor
                                    })
                            }

//                    StyledSelectBox(
//                        selectedLocalArmazenamento = localArmazenamento,
//                        onLocalArmazenamentoSelected = { novoValor ->
//                            localArmazenamento = novoValor
//                        }
//                    )
                            LocalArmazenamentoSelectBoxCadastrar(
                                selectedOption = localArmazenamento,
                                onLocalArmazenamentoChange = { novoValor ->
                                    localArmazenamento = novoValor
                                }
                            )

                        }
                    }

                    ImagemPassoMaipulavel1(onCadastroItemProximoClick = {
                        val novoEstoque = estoque.copy(
                            nome = nome,
                            lote = lote,
                            categoria = CategoriaEstoque.valueOf(selectedCategory.toString()),
                            marca = marca,
                            localArmazenamento = localArmazenamento
                        )
                        sharedViewModel.atualizarEstoque(novoEstoque)
                        onEdicaoItemManipuladoProximoClick()
                    })

                    Button(
                        onClick = {
                            if (formularioExibicao == "Formulario1") {
                                val novoEstoque = estoque.copy(
                                    nome = nome,
                                    lote = lote,
                                    categoria = CategoriaEstoque.valueOf(selectedCategory.toString()),
                                    marca = marca,
                                    localArmazenamento = localArmazenamento
                                )

                                sharedViewModel.atualizarEstoque(novoEstoque)
                                onEdicaoItemManipuladoProximoClick()
                            } else {
//                                // Verifica se há erros
//                                valorMedidaErro =
//                                    valorMedida.toDoubleOrNull()?.let { it < 0 } ?: true
//                                dataCadastroErro = try {
//                                    LocalDate.parse(dataCadastro, dateFormatter); false
//                                } catch (e: Exception) {
//                                    true
//                                }
//                                dataAvisoErro = try {
//                                    LocalDate.parse(dataAviso, dateFormatter); false
//                                } catch (e: Exception) {
//                                    true
//                                }
//                                dataAvisoAnteriorErro = try {
//                                    val avisoDate = LocalDate.parse(dataAviso, dateFormatter)
//                                    val cadastroDate = LocalDate.parse(dataCadastro, dateFormatter)
//                                    avisoDate.isBefore(cadastroDate)
//                                } catch (e: Exception) {
//                                    false
//                                }
//
//                                if (!valorMedidaErro && !dataCadastroErro && !dataAvisoErro && !dataAvisoAnteriorErro) {
////                            sharedViewModel.criarEstoqueAtualizado(
////                                estoqueEditar.copy(
////                                    unitario = qtdUnitaria.toInt(),
////                                    valorMedida = valorMedida.toDouble(),
////                                    dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
////                                    dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
////                                    localArmazenamento = estoqueEditar.localArmazenamento,
////                                    tipoMedida = Medidas.valueOf(tipoMedida)
////                                )
////                            )
//                                    val novoEstoque = estoqueEditar.copy(
//                                        unitario = qtdUnitaria.toInt(),
//                                        valorMedida = valorMedida.toDouble(),
//                                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
//                                        dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
//                                        localArmazenamento = estoqueEditar.localArmazenamento,
//                                        tipoMedida = Medidas.valueOf(tipoMedida)
//                                    )
//                                    sharedViewModel.atualizarEstoque(novoEstoque)
//                                    Log.d("Editar2Screen", "ESTOQUE: $novoEstoque")
//                                    sharedViewModel.estoque.value?.let {
//                                        Log.d("Editar2Screen", "ESTOQUE PARA REQUISICAO: $it")
//                                        sharedViewModel
//                                        viewModel.atualizarEstoque(context, idItem, it)
//                                    }
//                                }
                            }
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .width(155.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_AzulMarinho,
                            contentColor = colorResource(id = R.color.white)
                        )
                    ) {
                        if (formularioExibicao == "Formulario1") {
                            Text(
                                text = "Próximo",
                                color = White,
                                fontSize = 18.sp
                            )
                        } else {
                            if (estoqueState is EstoqueCriacaoState.Loading) {
                                CircularProgressIndicator(color = GI_AzulMarinho)
                            } else {
                                Text(
                                    text = "Salvar",
                                    color = White,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EdicaoManipulavelScreen() {
    EdicaoItemManipulavelScreen(
        sharedViewModel = SharedViewModel(),
        onEdicaoItemManipuladoVoltarClick = {},
        onEdicaoItemManipuladoProximoClick = {},
        estoque = EstoqueItemDiscriminator.Manipulado(
            nome = "Nome",
            lote = "Lote",
            categoria = CategoriaEstoque.OUTROS,
            localArmazenamento = "Local",
            manipulado = true,
            idItem = 1,
            tipoMedida = Medidas.UNIDADE,
            unitario = 1,
            valorMedida = 1.0,
            valorTotal = 1.0,
            dtaAviso = LocalDate.now().plusDays(1),
            dtaCadastro = LocalDate.now(),
            descricao = "Descrição",
            receita =
            ReceitaConsultaDto(
                idReceita = 1,
                receita = listOf()
            )
        ),
        estoqueViewModel = EstoqueViewModel(
            EstoqueRepositoryImplLocal()
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdicaoStyledSelectBox(
    selectedLocalArmazenamento: String,
    onLocalArmazenamentoSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(if (selectedLocalArmazenamento == "") "Selecione um local" else selectedLocalArmazenamento) }
    val options = listOf("Cozinha", "Armário", "Geladeira", "Freezer")

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)

    ) {
        Column(
            modifier = Modifier
                .size(width = 350.dp, height = 100.dp)

        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                text = "Local Armazenamento:",
                color = Black,
                fontSize = 22.sp,
                // fontFamily = JostRegular
            )
            // Usando ExposedDropdownMenuBox para um campo de seleção clicável
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },

                Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black
                    )// Alterna entre expandido e recolhido
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = { },
                    readOnly = true, // Define como somente leitura para funcionar como um select box
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(), // Garante que o menu dropdown apareça no lugar certo
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown"
                        )
                    },
                )

                // Menu Dropdown para as opções
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InputEdicaoManipulavel(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)

    ) {
        Column(
            modifier = Modifier
                .size(width = 350.dp, height = 100.dp)

        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                text = "$titulo:",
                color = Black,
                fontSize = 22.sp
            )
            OutlinedTextField(
                singleLine = true,
                value = valorCampo,
                onValueChange = { novoValorDoCampo ->
                    mudaValor(novoValorDoCampo)
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxSize()
                    .background(color = White, shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black
                    )
            )
        }

    }
}

@Composable
fun ExibirFormEdicaoManipulado(
    formularioExibicao: String,
    onFormularioExibicaoChange: (String) -> Unit
) {
    var selectedOptionIndex by remember { mutableStateOf(1) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                unselectedColor = Black,
            ),
            selected = selectedOptionIndex == 1,
            onClick = {
                selectedOptionIndex = 1
                onFormularioExibicaoChange("Option1")
            }
        )
        RadioButton(
            selected = selectedOptionIndex == 0,
            onClick = {
                selectedOptionIndex = 0
                onFormularioExibicaoChange("Option2")
            }
        )
    }
}