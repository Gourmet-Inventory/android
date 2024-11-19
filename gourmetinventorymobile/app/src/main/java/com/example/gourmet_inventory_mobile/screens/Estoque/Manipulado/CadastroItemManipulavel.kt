package com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.CategoriaEstoqueSelectBox
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.LocalArmazenamentoSelectBoxCadastrar
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White


@Composable
fun CadastroItemManipulavelScreen(
    sharedViewModel: SharedViewModel,
    onCadastroItemManipuladoVoltarClick: () -> Unit = {},
    onCadastroItemManipuladoProximoClick: () -> Unit = {}
) {
    val estoque by sharedViewModel.estoque.collectAsState()

    var nome by remember {
        mutableStateOf(estoque.nome)
    }
    var lote by remember {
        mutableStateOf(estoque.lote)
    }
    var categoria by remember {
        mutableStateOf(estoque.categoria.toString())
    }
    var selectedCategory by remember { mutableStateOf(if (estoque.categoria == null) CategoriaEstoque.OUTROS.name else estoque.categoria) }

    var localArmazenamento by remember {
        mutableStateOf(estoque.localArmazenamento)
    }

    var marca by remember {
        mutableStateOf(estoque.marca)
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = White)) {


        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize() ,
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
                            onCadastroItemManipuladoVoltarClick()
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
                    InputCadastroManipulavel(titulo = "Nome",valorCampo = nome,
                        mudaValor = { novoValor ->
                        nome = novoValor })

                    InputCadastroManipulavel(titulo = "Lote",valorCampo = lote,
                        mudaValor = { novoValor ->
                        lote = novoValor })

                    CategoriaEstoqueSelectBox(
                        selectedOption = CategoriaEstoque.valueOf(selectedCategory.toString()),
                        onCategoriaEstoqueChange = { newCategory ->
                            selectedCategory = CategoriaEstoque.valueOf(newCategory)
                        }
                    )

                    InputCadastroManipulavel(titulo = "Marca",valorCampo = marca,
                        mudaValor = { novoValor ->
                        marca = novoValor })

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

                    ImagemPassoMaipulavel1(onCadastroItemProximoClick = {
                        val novoEstoque = estoque.copy(
                            nome = nome,
                            lote = lote,
                            categoria = CategoriaEstoque.valueOf(selectedCategory.toString()),
                            marca = marca,
                            localArmazenamento = localArmazenamento
                        )
                        sharedViewModel.atualizarEstoque(novoEstoque)
                        onCadastroItemManipuladoProximoClick()
                    })

                    Button(
                        onClick = {
                            val novoEstoque = estoque.copy(
                                nome = nome,
                                lote = lote,
                                categoria = CategoriaEstoque.valueOf(selectedCategory.toString()),
                                marca = marca,
                                localArmazenamento = localArmazenamento
                            )

                            sharedViewModel.atualizarEstoque(novoEstoque)
                            onCadastroItemManipuladoProximoClick()
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
                        Text(
                            text = "Próximo",
                            color = White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 100.dp),
//            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
//        ) {
//            DownBarCadastroItemScreen(
//                onCadastroItemAcao1Click = onCadastroItemAcao1Click,
//                onCadastroItemAcao2Click = onCadastroItemAcao2Click,
//                onCadastroItemAcao3Click = onCadastroItemAcao3Click,
//                onCadastroItemAcao4Click = onCadastroItemAcao4Click
//            )
//        }
    }
}

@Preview
@Composable
fun CadastroManipulavelScreen() {
    CadastroItemManipulavelScreen(
        sharedViewModel = SharedViewModel(),
        onCadastroItemManipuladoVoltarClick = {},
        onCadastroItemManipuladoProximoClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledSelectBox(
    selectedLocalArmazenamento: String,
    onLocalArmazenamentoSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(if (selectedLocalArmazenamento == "") "Selecione um local" else selectedLocalArmazenamento) }
    val options = listOf("Cozinha", "Armário", "Geladeira", "Freezer")

    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)

    ){
        Column(
            modifier = Modifier
                .size(width = 350.dp, height = 100.dp)

        ){
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
fun InputCadastroManipulavel(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)

    ){
        Column(
            modifier = Modifier
                .size(width = 350.dp, height = 100.dp)

        ){
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

//@Composable
//fun DownBarCadastroItemScreen(
//    onCadastroItemAcao1Click: () -> Unit = {},
//    onCadastroItemAcao2Click: () -> Unit = {},
//    onCadastroItemAcao3Click: () -> Unit = {},
//    onCadastroItemAcao4Click: () -> Unit = {}
//) {
//    val context = LocalContext.current
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = GI_AzulMarinho)
//            .heightIn(70.dp),
////        horizontalArrangement = Arrangement.SpaceEvenly,
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.fornecedores_db),
//            contentDescription = "Ação 1",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onCadastroItemAcao1Click()
//                }
//        )
////        Spacer(modifier = Modifier.height(60.dp))
//        Image(
//            painter = painterResource(id = R.drawable.opened_box),
//            contentDescription = "Ação 2",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onCadastroItemAcao2Click()
//                }
//        )
//        Image(
//            painter = painterResource(id = cart),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onCadastroItemAcao3Click()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 4",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    onCadastroItemAcao4Click()
//                }
//        )
//    }
//}

//@Preview
//@Composable
//fun DownBarCadastroItemScreenPreview() {
//    DownBarCadastroItemScreen()
//}

@Composable
fun ImagemPassoMaipulavel1(
    onCadastroItemProximoClick: () -> Unit = {}
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
                selectedColor = GI_Laranja,
                unselectedColor = Black,
            ),
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
        RadioButton(
            selected = selectedOptionIndex == 0,
            onClick = { onCadastroItemProximoClick() }
        )
    }
}