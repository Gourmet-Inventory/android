package com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado

import SharedViewModel
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueConsultaState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueCriacaoState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun Editar2Screen(
    estoqueViewModel: EstoqueViewModel,
    sharedViewModel: SharedViewModel,
    onEditarItem2AnteriorClick: (EstoqueCriacao?) -> Unit,
    onEditarItem2SalvarClick: () -> Unit,
    idItem: Long
) {
    val estoqueEditar by sharedViewModel.estoque.collectAsState()
    Log.d("Editar2Screen", "estoqueEditar: $estoqueEditar")

    var estoque: EstoqueItemDiscriminator.Industrializado? = null
    idItem?.let { id ->
        estoque =
            (estoqueViewModel.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                ?.estoqueConsulta
                ?.mapNotNull { it as? EstoqueItemDiscriminator.Industrializado }
                ?.find { it.idItem == id}
    }
    Log.d("Editar2Screen", "estoque: $estoque")

    val qtdUnitariaEntrada = if (estoqueEditar.unitario != 0) estoqueEditar!!.unitario.toString() else estoque?.unitario.toString()
    var qtdUnitaria by remember {
        mutableStateOf(qtdUnitariaEntrada)
    }
    val valorMedidaEntrada = if (estoqueEditar.valorMedida != 0.0) estoqueEditar!!.valorMedida.toString() else estoque?.valorMedida.toString()
    var valorMedida by remember {
        mutableStateOf(valorMedidaEntrada)
    }
    val dataCadastroEntrada = if (estoqueEditar.dtaCadastro != LocalDate.now()) estoqueEditar!!.dtaCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) else estoque?.dtaCadastro?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        .toString()
    var dataCadastro by remember {
        mutableStateOf(dataCadastroEntrada)
    }
    val dataAvisoEntrada = if (estoqueEditar.dtaAviso != LocalDate.now().plusDays(1)) estoqueEditar!!.dtaAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) else estoque?.dtaAviso?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    var dataAviso by remember {
        mutableStateOf(dataAvisoEntrada)
    }
    val tipoMedidaEntrada = if (estoqueEditar.tipoMedida != null) estoqueEditar!!.tipoMedida.toString() else estoque?.tipoMedida.toString()
    var tipoMedida by remember {
        mutableStateOf(tipoMedidaEntrada)
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

//    fun criarEstoqueAtualizado(): EstoqueCriacao? {
//        Log.d("Editar2Screen", "Criando EstoqueCriacao")
//        return try {
//            EstoqueCriacao(
//                lote = estoque?.lote ?: "",
//                manipulado = estoque?.manipulado ?: false,
//                nome = estoque?.nome ?: "",
//                categoria = estoque?.categoria ?: "",
//                tipoMedida = Medidas.valueOf(tipoMedida),
//                unitario = qtdUnitaria.toIntOrNull() ?: 0,
//                valorMedida = valorMedida.toDoubleOrNull() ?: 0.0,
//                localArmazenamento = estoque?.localArmazenamento ?: "",
//                dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
//                dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
//                marca = estoque?.marca ?: ""
//            )
//        } catch (e: Exception) {
//            Log.e("Editar2Screen", "Erro ao criar EstoqueCriacao: ${e.message}")
//            null
//        }
//    }

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
                    val novoEstoque = estoqueEditar.copy(
                        unitario = qtdUnitaria.toInt(),
                        valorMedida = valorMedida.toDouble(),
                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                        dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                        localArmazenamento = estoqueEditar.localArmazenamento,
                        tipoMedida = Medidas.valueOf(tipoMedida)
                    )
                    sharedViewModel.atualizarEstoque(novoEstoque)
                    onEditarItem2AnteriorClick(novoEstoque)
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
                    text = "Editar Item:",
                    color = Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 35.sp, fontFamily = JostBold)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                    TipoMedidaSelectBoxEdicao(tipoMedida, onTipoMedidaChange = { tipoMedida = it })
                    InputEdicao2(
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
                    InputEdicao2(
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

                    dataAviso?.let {
                        InputEdicao2(
                            titulo = "Data Aviso",
                            placeholder = "dd/mm/aaaa",
                            valorCampo = it,
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
            }
            Spacer(modifier = Modifier.height(100.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Passo2Edicao(onEditarItem2AnteriorClick = {
                    val novoEstoque = estoqueEditar.copy(
                        unitario = qtdUnitaria.toInt(),
                        valorMedida = valorMedida.toDouble(),
                        dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                        dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                        localArmazenamento = estoqueEditar.localArmazenamento,
                        tipoMedida = Medidas.valueOf(tipoMedida)
                    )
                    sharedViewModel.atualizarEstoque(novoEstoque)
                    onEditarItem2AnteriorClick(novoEstoque)
                })

                Button(
                    onClick = {
                        // Verifica se há erros
                        valorMedidaErro = valorMedida.toDoubleOrNull()?.let { it < 0 } ?: true
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
//                            sharedViewModel.criarEstoqueAtualizado(
//                                estoqueEditar.copy(
//                                    unitario = qtdUnitaria.toInt(),
//                                    valorMedida = valorMedida.toDouble(),
//                                    dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
//                                    dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
//                                    localArmazenamento = estoqueEditar.localArmazenamento,
//                                    tipoMedida = Medidas.valueOf(tipoMedida)
//                                )
//                            )
                            val novoEstoque = estoqueEditar.copy(
                                unitario = qtdUnitaria.toInt(),
                                valorMedida = valorMedida.toDouble(),
                                dtaCadastro = LocalDate.parse(dataCadastro, dateFormatter),
                                dtaAviso = LocalDate.parse(dataAviso, dateFormatter),
                                localArmazenamento = estoqueEditar.localArmazenamento,
                                tipoMedida = Medidas.valueOf(tipoMedida)
                            )
                            sharedViewModel.atualizarEstoque(novoEstoque)
                            Log.d("Editar2Screen", "ESTOQUE: $novoEstoque")
                            sharedViewModel.estoque.value?.let {
                                Log.d("Editar2Screen", "ESTOQUE PARA REQUISICAO: $it")
                                viewModel.atualizarEstoque(context, idItem ,it)
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
                        Text(text = "Editar", color = Black, fontSize = 18.sp)
                    }
                }
            }

            LaunchedEffect(estoqueState) {
                if (estoqueState is EstoqueCriacaoState.Success) {
                    Toast.makeText(context, "Edição efetuada com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    onEditarItem2SalvarClick()
                }
            }

            LaunchedEffect(estoqueState) {
                if (estoqueState is EstoqueCriacaoState.Error) {
                    Log.e(
                        "Editar2Screen",
                        "ERRO: " + (estoqueState as EstoqueCriacaoState.Error).message
                    )
                    Toast.makeText(context, "Erro ao editar item", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipoMedidaSelectBoxEdicao(selectedOption: String, onTipoMedidaChange: (String) -> Unit) {
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
                modifier = Modifier.background(color = White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Medidas.values().forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            onTipoMedidaChange(selectionOption.name)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun Edicao2ScreenPreview() {
//    Editar2Screen(
//        sharedViewModel = SharedViewModel(),
//        onEditarItem2AnteriorClick = {},
//        onEditarItem2SalvarClick = {},
//        idItem = 1
//    )
//}

@Composable
fun InputEdicao2(
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
            .height(140.dp)
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
fun Passo2Edicao(
    onEditarItem2AnteriorClick: (EstoqueCriacao?) -> Unit
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
                onEditarItem2AnteriorClick(null)
            }
        )
        RadioButton(
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
    }
}