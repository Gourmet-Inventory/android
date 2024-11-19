package com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado

import ItensReceita
import SharedViewModel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.screens.Estoque.AddButton
import com.example.gourmet_inventory_mobile.screens.Estoque.EscolhaTipoDeEstoque
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.InputCadastro2
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.Passo2Criacao
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.TipoMedidaSelectBox
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueCriacaoState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CadastroItemManipulavel2Screen(
    sharedViewModel: SharedViewModel,
    onCadastroItemManipulavel2AnteriorClick: (EstoqueCriacao?) -> Unit,
    onCadastroItemManipulavelCadastrarClick: (EstoqueConsulta?) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    val estoque by sharedViewModel.estoque.collectAsState()
    Log.d("CadastroItem2Screen", "Estoque: $estoque")

    var qtdUnitaria by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.unitario.toString() else ""
        )
    }
    var valorMedida by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.valorMedida.toString() else ""
        )
    }
    var dataCadastro by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.dtaCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) else ""
        )
    }
    var dataAviso by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.dtaAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) else ""
        )
    }
    var tipoMedida by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.tipoMedida.name else ""
        )
    }

    val context = LocalContext.current
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val viewModel = koinViewModel<EstoqueViewModel>()
    val estoqueState by viewModel.estoqueCriacaoState.collectAsState()

    var valorMedidaErro by remember { mutableStateOf(false) }
    var dataCadastroErro by remember { mutableStateOf(false) }
    var dataAvisoErro by remember { mutableStateOf(false) }
    var dataAvisoAnteriorErro by remember { mutableStateOf(false) }

    fun criarEstoqueAtualizado(): EstoqueCriacao? {
        Log.d("CadastroItem2Screen", "Criando EstoqueCriacao")
        return try {
            EstoqueCriacao(
                lote = estoque?.lote ?: "",
                manipulado = estoque?.manipulado ?: false,
                nome = estoque?.nome ?: "",
                categoria = estoque?.categoria ?: CategoriaEstoque.OUTROS,
                tipoMedida = Medidas.valueOf(tipoMedida),
                unitario = qtdUnitaria.toIntOrNull() ?: 0,
                valorMedida = valorMedida.toDoubleOrNull() ?: 0.0,
                localArmazenamento = estoque?.localArmazenamento ?: "",
                dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                marca = estoque?.marca ?: ""
            )
        } catch (e: Exception) {
            Log.e("CadastroItemManipulavel2Screen", "Erro ao criar EstoqueCriacao: ${e.message}")
            null
        }
    }

    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    val novoEstoque = estoque.copy(
                        unitario = qtdUnitaria.toInt(),
                        valorMedida = valorMedida.toDouble(),
                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                        dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                        tipoMedida = Medidas.valueOf(tipoMedida)
                    )
                    sharedViewModel.atualizarEstoque(novoEstoque)
                    onCadastroItemManipulavel2AnteriorClick(estoque)
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Cadastrar Item",
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

            //Inicio dos dados do formulário
            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Quantidade Unitária
                Row(
                    modifier = Modifier
                        .width(350.dp)
                        .height(70.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        fontSize = 22.sp,
                        modifier = Modifier.width(160.dp),
                        text = "Quantidade Unitária:"
                    )

                    OutlinedTextField(
                        value = qtdUnitaria,
                        onValueChange = { qtdUnitaria = it },
                        modifier = Modifier
                            .background(color = White, shape = RoundedCornerShape(5.dp))
                            .border(1.dp, Color.Black)
                            .width(180.dp),
                        singleLine = true,
                    )
                }

                //Data de Aviso
                //Data de Cadastro
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    InputCadastro2(
                        titulo = "Data Cadastro",
                        placeholder = "dd/mm/aaaa",
                        valorCampo = dataCadastro,
                        mudaValor = { novoValor ->
                            dataCadastro = novoValor
                            dataCadastroErro = try {
                                LocalDate.parse(novoValor, dateFormatter)
                                false
                            } catch (e: Exception) {
                                true
                            }
                        },
                        isErro = dataCadastroErro,
                        mensagemErro = "Data inválida (dd/mm/aaaa)"
                    )

                    InputCadastro2(
                        titulo = "Data Aviso",
                        placeholder = "dd/mm/aaaa",
                        valorCampo = dataAviso,
                        mudaValor = { novoValor ->
                            dataAviso = novoValor
                            dataAvisoErro = try {
                                val avisoDate = LocalDate.parse(novoValor, dateFormatter)
                                val cadastroDate = LocalDate.parse(dataCadastro, dateFormatter)
                                dataAvisoAnteriorErro = avisoDate.isBefore(cadastroDate)
                                false
                            } catch (e: Exception) {
                                true
                            }
                        },
                        isErro = dataAvisoErro || dataAvisoAnteriorErro,
                        mensagemErro = if (dataAvisoErro) "Data inválida (dd/mm/aaaa)" else "Data Aviso não pode ser anterior à Data Cadastro"
                    )
                }

                //Tipo Medida
                //Valor Medida
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TipoMedidaSelectBox(tipoMedida, onTipoMedidaChange = { tipoMedida = it })
                    InputCadastro2(
                        titulo = "Valor Medida",
                        placeholder = "",
                        valorCampo = valorMedida,
                        mudaValor = { novoValor ->
                            valorMedida = novoValor
                            valorMedidaErro = novoValor.toDoubleOrNull()?.let { it <= 0 } ?: true
                        },
                        isErro = valorMedidaErro,
                        mensagemErro = "Valor inválido"
                    )
                }

                //Linha comm info Receita e botão adicionar ingrediente
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "Receita",
                        color = Black,
                        fontSize = 22.sp
                    )
                    AddButton(onAddClick = { showDialog = true }, containerColor = GI_Laranja)
                }

                //Visualização de itens da receita
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .height(170.dp)
                        .border(1.dp, color = Color.Black)
                ) {
                    ItensReceita(receitas = sharedViewModel.receita)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Passo2Criacao(onCadastroItemAnteriorClick = {
                        val novoEstoque = estoque.copy(
                            unitario = qtdUnitaria.toInt(),
                            valorMedida = valorMedida.toDouble(),
                            tipoMedida = Medidas.valueOf(tipoMedida),
                            dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                            dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                        )
                        sharedViewModel.atualizarEstoque(novoEstoque)
                        onCadastroItemManipulavel2AnteriorClick(estoque)
                    })

                    Button(
                        onClick = {
                            // Verifica se há erros
                            valorMedidaErro = valorMedida.toDoubleOrNull()?.let { it <= 0 } ?: true
                            dataCadastroErro = try {
                                LocalDate.parse(dataCadastro, dateFormatter); false
                            } catch (e: Exception) {
                                true
                            }
                            dataAvisoErro = try {
                                LocalDate.parse(dataAviso, dateFormatter); false
                            } catch (e: Exception) {
                                true
                            }
                            dataAvisoAnteriorErro = try {
                                val avisoDate = LocalDate.parse(dataAviso, dateFormatter)
                                val cadastroDate = LocalDate.parse(dataCadastro, dateFormatter)
                                avisoDate.isBefore(cadastroDate)
                            } catch (e: Exception) {
                                false
                            }

                            if (!valorMedidaErro && !dataCadastroErro && !dataAvisoErro && !dataAvisoAnteriorErro) {
                                criarEstoqueAtualizado()?.let { novoEstoque ->
                                    Log.d("CadastroItemManipulavel2Screen", "Cadastrando Estoque: $novoEstoque")
                                    sharedViewModel.atualizarEstoque(novoEstoque)
                                    viewModel.cadastrarEstoque(context, novoEstoque)
                                }
                            }
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .width(155.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GI_Laranja)
                    ) {
                        if (estoqueState is EstoqueCriacaoState.Loading) {
                            CircularProgressIndicator(color = GI_AzulMarinho)
                        } else {
                            Text(text = "Cadastrar", color = Black, fontSize = 18.sp)
                        }
                    }

//                    LaunchedEffect(estoqueState) {
//                        if (estoqueState is EstoqueCriacaoState.Success) {
//                            Toast.makeText(context, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT)
//                                .show()
//                            sharedViewModel.limparEstoque()
//                            onCadastroItemManipulavelCadastrarClick((estoqueState as EstoqueCriacaoState.Success).estoqueConsulta)
//                        }
//                    }
//
//                    LaunchedEffect(estoqueState) {
//                        if (estoqueState is EstoqueCriacaoState.Error) {
//                            Log.e(
//                                "CadastroItemManipulavel2Screen",
//                                "ERRO: " + (estoqueState as EstoqueCriacaoState.Error).message
//                            )
//                            Toast.makeText(context, "Erro ao cadastrar item", Toast.LENGTH_SHORT).show()
//                        }
//                    }

                    if (showDialog) {

                    }
                }
            }
        }
    }
}

//@Composable
//fun AddReceita(
//    onListaEstoqueClick: (String) -> Unit,
//    showDialog: Boolean,
//    onDismiss: () -> Unit
//) {
//    if (showDialog) {
//        AlertDialog(
//            modifier = Modifier
//                .background(Color.White, shape = RoundedCornerShape(10.dp)),
//            onDismissRequest = { onDismiss() },
//            containerColor = Color.White,
//            title = {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Adicionar Ingrediente:",
//                        modifier = Modifier
//                            .padding(bottom = 16.dp)
//                            .align(Alignment.CenterVertically),
//                    )
//                }
//            },
//            confirmButton = {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    var ingrediente by remember { mutableStateOf("") }
//                    var expandedIngrediente by remember { mutableStateOf(false) }
//                    val ingredientes = listOf<EstoqueItemDiscriminator.Industrializado>()
//
//                    ExposedDropdownMenuBox(
//                        expanded = expandedIngrediente,
//                        onExpandedChange = { expandedIngrediente = !expandedIngrediente }
//                    ) {
//                        OutlinedTextField(
//                            value = ingrediente,
//                            onValueChange = { ingrediente = it },
//                            label = { Text("Ingrediente") },
//                            readOnly = true,
//                            trailingIcon = {
//                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedIngrediente)
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                        ExposedDropdownMenu(
//                            expanded = expandedIngrediente,
//                            onDismissRequest = { expandedIngrediente = false }
//                        ) {
//                            ingredientes.forEach { selectionOption ->
//                                DropdownMenuItem(
//                                    onClick = {
//                                        ingrediente = selectionOption
//                                        expandedIngrediente = false
//                                    }
//                                ) {
//                                    Text(text = selectionOption)
//                                }
//                            }
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    var tipoMedida by remember { mutableStateOf("") }
//
//                    // Tipo de Medida
//                    TipoMedidaSelectBox(tipoMedida, onTipoMedidaChange = { tipoMedida = it })
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    var valor by remember { mutableStateOf("") }
//                    OutlinedTextField(
//                        value = valor,
//                        onValueChange = { valor = it },
//                        label = { Text("Valor") },
//                        modifier = Modifier.fillMaxWidth(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    SmallFloatingActionButton(
//                        onClick = { /* Adicionar lógica de adicionar ingrediente */ },
//                        containerColor = GI_Laranja,
//                        contentColor = White
//                    ) {
//                        Icon(Icons.Filled.Add, contentDescription = "Adicionar")
//                    }
//                }
//            }
//        )
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EscolhaIngredienteSelectBox(selectedOption: String, onEscolhaIngerienteChange: (EstoqueItemDiscriminator.Industrializado) -> Unit) {
//    var expanded by remember { mutableStateOf(false) }
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded }
//    ) {
//        Column {
//            Text(
//                modifier = Modifier
//                    .padding(top = 10.dp)
//                    .height(30.dp),
//                text = "Ingrediente:",
//                color = Black,
//                fontSize = 22.sp
//            )
//            OutlinedTextField(
//                value = selectedOption,
//                onValueChange = { },
//                modifier = Modifier
//                    .width(160.dp)
//                    .background(color = White, shape = RoundedCornerShape(5.dp))
//                    .menuAnchor(),
//                trailingIcon = {
//                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
//                },
//                readOnly = true,
//                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
//            )
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                modifier = Modifier.background(color = White)
//            ) {
//                Medidas.values().forEach { selectionOption ->
//                    DropdownMenuItem(
//                        text = { Text(selectionOption.name) },
//                        onClick = {
//                            onEscolhaIngerienteChange(
//                                EstoqueItemDiscriminator.Industrializado(
//                                    idItem = 0,
//                                    manipulado = false,
//                                    lote = "",
//                                    nome = "",
//                                    categoria = CategoriaEstoque.OUTROS,
//                                    tipoMedida = Medidas.UNIDADE,
//                                    unitario = 0,
//                                    valorMedida = 0.0,
//                                    valorTotal = 0.0,
//                                    localArmazenamento = "",
//                                    dtaCadastro = LocalDate.now(),
//                                    dtaAviso = LocalDate.now(),
//                                    marca = ""
//                                )
//                            )
//                            expanded = false
//                        }
//                    )
//                }
//            }
//        }
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun CadastroItemManipulavel2ScreenPreview(modifier: Modifier = Modifier) {
    CadastroItemManipulavel2Screen(
        sharedViewModel = SharedViewModel(),
        onCadastroItemManipulavel2AnteriorClick = {},
        onCadastroItemManipulavelCadastrarClick = {}
    )
}