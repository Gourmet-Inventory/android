package com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado

import SharedViewModel
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

@Composable
fun CadastroItemScreen(
    sharedViewModel: SharedViewModel,
    onCadastroItemVoltarClick: () -> Unit,
    onCadastroItemProximoClick: () -> Unit
) {
    val estoque by sharedViewModel.estoque.collectAsState()

    var nome by remember { mutableStateOf(estoque.nome) }
    var lote by remember { mutableStateOf(estoque.lote) }
    var selectedCategory by remember {
        mutableStateOf(
            if (estoque != null) estoque!!.tipoMedida.name else ""
        )
    }
    var marca by remember { mutableStateOf(estoque.marca) }

    // Erros
    var nomeErro by remember { mutableStateOf(false) }
    var loteErro by remember { mutableStateOf(false) }
    var categoriaErro by remember { mutableStateOf(false) }
    var marcaErro by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        LazyColumn {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Botão de Voltar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp, start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { onCadastroItemVoltarClick() }) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Voltar",
                                Modifier.size(44.dp),
                                tint = Color.Black
                            )
                        }
                    }

                    // Título da Tela
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
                            style = TextStyle(fontSize = 35.sp, fontFamily = JostBold)
                        )
                    }

                    // Campos de Entrada
                    InputCadastro("Nome", nome, { novoValor ->
                        nome = novoValor
                        nomeErro = nome.isBlank()
                    }, nomeErro, "Campo obrigatório")

                    InputCadastro("Lote", lote, { novoValor ->
                        lote = novoValor
                        loteErro = lote.isBlank()
                    }, loteErro, "Campo obrigatório")

//                    InputCadastro("Categoria", categoria, { novoValor ->
//                        categoria = novoValor
//                        categoriaErro = categoria.isBlank()
//                    }, categoriaErro, "Campo obrigatório")

                    CategoriaEstoqueSelectBox(
                        selectedOption = selectedCategory,
                        onCategoriaEstoqueChange = { newCategory ->
                            selectedCategory = newCategory
                            // Update your UI or logic based on the selected category
                        }
                    )

                    InputCadastro("Marca", marca, { novoValor ->
                        marca = novoValor
                        marcaErro = marca.isBlank()
                    }, marcaErro, "Campo obrigatório")

                    // Componente de ImagemPasso1
                    ImagemPasso1(
                        nome = nome,
                        lote = lote,
                        categoria = selectedCategory,
                        marca = marca,
                        onCadastroItemProximoClick = {
                            val novoEstoque = estoque.copy(
                                nome = nome,
                                lote = lote,
                                categoria = selectedCategory,
                                marca = marca
                            )
                            sharedViewModel.atualizarEstoque(novoEstoque)
                            onCadastroItemProximoClick()
                        },
                        atualizaErros = { erros ->
                            nomeErro = erros.nome
                            loteErro = erros.lote
                            categoriaErro = erros.categoria
                            marcaErro = erros.marca
                        },
                        estoque = estoque
                    )

                    // Botão Próximo
                    Button(
                        onClick = {
                            // Atualiza os erros ao clicar em "Próximo"
                            nomeErro = nome.isBlank()
                            loteErro = lote.isBlank()
                            categoriaErro = selectedCategory.isBlank()
                            marcaErro = marca.isBlank()

                            // Se não houver erros, navegue para a próxima tela
                            if (!nomeErro && !loteErro && !categoriaErro && !marcaErro) {
                                val novoEstoque = estoque.copy(
                                    nome = nome,
                                    lote = lote,
                                    categoria = selectedCategory,
                                    marca = marca
                                )
                                sharedViewModel.atualizarEstoque(novoEstoque)
                                onCadastroItemProximoClick()
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
                        Text(text = "Próximo", color = White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun InputCadastro(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit,
    isErro: Boolean = false,
    mensagemErro: String = ""
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Column(modifier = Modifier.size(width = 350.dp, height = 140.dp)) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                text = "$titulo:",
                color = Black,
                fontSize = 22.sp
            )
            OutlinedTextField(
                value = valorCampo,
                onValueChange = { novoValorDoCampo -> mudaValor(novoValorDoCampo) },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxSize()
                    .background(color = White, shape = RoundedCornerShape(5.dp))
                    .border(width = 1.dp, color = Color.Black),
                isError = isErro,
                singleLine = true
            )
            if (isErro) {
                Text(
                    text = mensagemErro,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }
        }
    }
}

data class ErrosCadastro(
    val nome: Boolean,
    val lote: Boolean,
    val categoria: Boolean,
    val marca: Boolean
)

@Composable
fun ImagemPasso1(
    nome: String,
    lote: String,
    categoria: String,
    marca: String,
    onCadastroItemProximoClick: (estoque: EstoqueCriacao?) -> Unit = {},
    atualizaErros: (ErrosCadastro) -> Unit,
    estoque: EstoqueCriacao? = null
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
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
        RadioButton(
            selected = selectedOptionIndex == 0,
            onClick = {
                val erros = ErrosCadastro(
                    nome.isBlank(),
                    lote.isBlank(),
                    categoria.isBlank(),
                    marca.isBlank()
                )

                atualizaErros(erros)

                if (!erros.nome && !erros.lote && !erros.categoria && !erros.marca) {

                    onCadastroItemProximoClick(estoque)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaEstoqueSelectBox(selectedOption: String, onCategoriaEstoqueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

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
                    text = "Categoria Estoque:",
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
                    onDismissRequest = { expanded = false }
                ) {
                    CategoriaEstoque.values().forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption.name) },
                            onClick = {
                                onCategoriaEstoqueChange(
                                    selectionOption.name
                                )
                                expanded = false
                            }
                        )
                    }
                }
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                Medidas.values().forEach { selectionOption ->
//                    DropdownMenuItem(
//                        text = { Text(selectionOption.name) },
//                        onClick = {
//                            onTipoMedidaChange(selectionOption.name)
//                            expanded = false
//                        }
//                    )
//                }
//            }
            }
        }
    }
}


@Preview
@Composable
fun CadastroScreenPreview(): Unit {
    CadastroItemScreen(
        sharedViewModel = SharedViewModel(),
        onCadastroItemVoltarClick = {},
        onCadastroItemProximoClick = {}
    )
}