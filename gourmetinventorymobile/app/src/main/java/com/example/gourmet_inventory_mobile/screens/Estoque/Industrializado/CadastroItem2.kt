package com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado

import SharedViewModel
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueConsulta
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
fun CadastroItem2Screen(
    sharedViewModel: SharedViewModel,
    onCadastroItem2AnteriorClick: (EstoqueCriacaoDto?) -> Unit,
    onCadastroItemCadastrarClick: (EstoqueConsulta?) -> Unit,
    estoque: EstoqueCriacaoDto?
) {
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
    var localArmazenamento by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.localArmazenamento else ""
        )
    }
    var tipoMedida by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.tipoMedida.name else ""
        )
    }

    // Erros
    var valorMedidaErro by remember { mutableStateOf(false) }
    var dataCadastroErro by remember { mutableStateOf(false) }
    var dataAvisoErro by remember { mutableStateOf(false) }
    var dataAvisoAnteriorErro by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val viewModel = koinViewModel<EstoqueViewModel>()
    val estoqueState by viewModel.estoqueCriacaoState.collectAsState()

    fun criarEstoqueAtualizado(): EstoqueCriacaoDto? {
        Log.d("CadastroItem2Screen", "Criando EstoqueCriacao")
        return try {
            EstoqueCriacaoDto(
                lote = estoque?.lote ?: "",
                manipulado = estoque?.manipulado ?: false,
                nome = estoque?.nome ?: "",
                categoria = estoque?.categoria ?: CategoriaEstoque.OUTROS,
                tipoMedida = Medidas.valueOf(tipoMedida),
                unitario = qtdUnitaria.toIntOrNull() ?: 0,
                valorMedida = valorMedida.toDoubleOrNull() ?: 0.0,
                localArmazenamento = localArmazenamento,
                dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                marca = estoque?.marca ?: ""
            )
        } catch (e: Exception) {
            Log.e("CadastroItem2Screen", "Erro ao criar EstoqueCriacao: ${e.message}")
            null
        }
    }


    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Cabeçalho
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    val novoEstoque = estoque.copy(
                        unitario = qtdUnitaria.toInt(),
                        valorMedida = valorMedida.toDouble(),
                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                        dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                        localArmazenamento = localArmazenamento,
                        tipoMedida = Medidas.valueOf(tipoMedida)
                    )
                    sharedViewModel.atualizarEstoque(novoEstoque)
                    onCadastroItem2AnteriorClick(estoque)
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                        Modifier.size(44.dp),
                        tint = Color.Black
                    )
                }
            }

            // Título
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cadastrar Item:",
                    color = Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 35.sp, fontFamily = JostBold)
                )
            }

            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LocalArmazenamentoSelectBoxCadastrar(
                    localArmazenamento,
                    onLocalArmazenamentoChange = { localArmazenamento = it })

                Row(
                    modifier = Modifier
                        .width(350.dp)
                        .height(100.dp),
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
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

                Row {
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
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Passo2Criacao(onCadastroItemAnteriorClick = {
                    val novoEstoque = estoque.copy(
                        unitario = qtdUnitaria.toInt(),
                        valorMedida = valorMedida.toDouble(),
                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                    dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                    )
                    sharedViewModel.atualizarEstoque(novoEstoque)
                    onCadastroItem2AnteriorClick(estoque)
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
                                Log.d("CadastroItem2Screen", "Cadastrando Estoque: $novoEstoque")
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
            }

            LaunchedEffect(estoqueState) {
                if (estoqueState is EstoqueCriacaoState.Success) {
                    Toast.makeText(context, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    sharedViewModel.limparEstoque()
                    onCadastroItemCadastrarClick((estoqueState as EstoqueCriacaoState.Success).estoqueConsulta)
                }
            }

            LaunchedEffect(estoqueState) {
                if (estoqueState is EstoqueCriacaoState.Error) {
                    Log.e(
                        "CadastroItem2Screen",
                        "ERRO: " + (estoqueState as EstoqueCriacaoState.Error).message
                    )
                    Toast.makeText(context, "Erro ao cadastrar item", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Cadastro2ScreenPreview() {
    CadastroItem2Screen(
        onCadastroItem2AnteriorClick = {},
        onCadastroItemCadastrarClick = {},
        estoque = EstoqueCriacaoDto(
            lote = "123",
            manipulado = true,
            nome = "Nome",
            categoria = CategoriaEstoque.OUTROS,
            tipoMedida = Medidas.UNIDADE,
            unitario = 1,
            valorMedida = 1.0,
            localArmazenamento = "Local",
            dtaCadastro = LocalDate.now(),
            dtaAviso = LocalDate.now(),
            marca = "Marca"
        ),
        sharedViewModel = SharedViewModel()
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoMedidaSelectBox(selectedOption: String, onTipoMedidaChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                text = "Tipo Medida:",
                color = Black,
                fontSize = 22.sp
            )
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { },
                modifier = Modifier
                    .width(160.dp)
                    .background(color = White, shape = RoundedCornerShape(5.dp))
                    .menuAnchor(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
//                modifier = Modifier.background(color = White)
            ) {
                Medidas.values().forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.nomeExibicao) },
                        onClick = {
                            onTipoMedidaChange(selectionOption.nomeExibicao)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalArmazenamentoSelectBoxCadastrar(
    selectedOption: String,
    onLocalArmazenamentoChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Cozinha", "Armário", "Geladeira", "Freezer")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Row(
            modifier = Modifier
                .width(350.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(30.dp),
                    text = "Local Armazenamento:",
                    color = Black,
                    fontSize = 22.sp
                )
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = White, shape = RoundedCornerShape(5.dp))
                        .menuAnchor(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    readOnly = true,
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
//                    modifier = Modifier.background(color = White)
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                onLocalArmazenamentoChange(selectionOption)
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
fun InputCadastro2(
    titulo: String,
    placeholder: String,
    valorCampo: String,
    mudaValor: (String) -> Unit,
    isErro: Boolean,
    mensagemErro: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(180.dp)
            .height(130.dp)
    ) {
        Column(
            modifier = Modifier
                .size(width = 380.dp, height = 100.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                text = "$titulo:",
                color = Black,
                fontSize = 22.sp
//                fontFamily = JostRegular
            )
            OutlinedTextField(
                value = valorCampo,
                onValueChange = { novoValorDoCampo ->
                    mudaValor(novoValorDoCampo)
                },
                modifier = Modifier
                    .height(55.dp)
                    .width(160.dp)
                    .background(color = White, shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = if (isErro) Color.Red else Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    ),
                placeholder = {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                    )
                },
                singleLine = true,
                isError = isErro,
                keyboardOptions = if (titulo == "Valor Medida") KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default
            )
            if (isErro) {
                Text(
                    text = mensagemErro,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun Passo2Criacao(
    onCadastroItemAnteriorClick: () -> Unit = {}
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
            selected = selectedOptionIndex == 0,
            onClick = {
                onCadastroItemAnteriorClick()
            }
        )
        RadioButton(
            colors = RadioButtonDefaults.colors(
//                selectedColor = GI_AzulMarinho,
                unselectedColor = Black,
            ),
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
    }
}