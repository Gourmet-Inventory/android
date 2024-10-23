package com.example.gourmet_inventory_mobile.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

//class Cadastro2Activity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                Cadastro2Screen()
//
//            }
//        }
//    }
//}

@Composable
fun CadastroItem2Screen(
    onCadastroItem2AnteriorClick: () -> Unit,
    onCadastroItemCadastrarClick: () -> Unit
) {
    var qtdUnitaria by remember {
        mutableStateOf("")
    }
    var tipoMedida by remember {
        mutableStateOf("")
    }
    var valorMedida by remember {
        mutableStateOf("")
    }
    var dataCadastro by remember {
        mutableStateOf("")
    }
    var dataAviso by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onCadastroItem2AnteriorClick()
                }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                        Modifier.size(44.dp),
                        tint = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
//                    .padding(top = 45.dp, start = 26.dp, end = 26.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cadastrar Item:",
                    modifier = Modifier,
                    color = Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = JostBold
                    )
                )
            }
            Column (
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier
                        .width(350.dp)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically){

                    Text(
                        fontSize = 22.sp,
                        modifier = Modifier
                            .width(160.dp),
                        text = "Quantidade Unitária:"
                    )

                    OutlinedTextField(
                        value = qtdUnitaria,
                        onValueChange = {qtdUnitaria = it},
                        modifier = Modifier
                            .background(color = White, shape = RoundedCornerShape(5.dp))
                            .border(
                                width = 1.dp,
                                color = Color.Black
                            )
                            .width(180.dp),
                        singleLine = true,
                    )
                }

                Row {
                    InputCadastro2(titulo = "Data Cadastro", placeholder = "dd/mm/aaaa", valorCampo = dataCadastro, mudaValor = { novoValor ->
                        dataCadastro = novoValor })

                    InputCadastro2(titulo = "Data Aviso", placeholder = "dd/mm/aaaa", valorCampo = dataAviso, mudaValor = { novoValor ->
                        dataAviso = novoValor })
                }

                Column (modifier = Modifier, verticalArrangement = Arrangement.spacedBy(50.dp)) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        InputCadastro2(titulo = "Data Cadastro", placeholder = "dd/mm/aaaa", valorCampo = dataCadastro, mudaValor = { novoValor ->
                            dataCadastro = novoValor })
                        Row (
//                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 30.dp)
                        ) {

                            AddrReceita(onAddClick = {})
                        }
                    }
                }

                Row {
                    InputCadastro2(titulo = "Tipo Medida", placeholder = "", valorCampo = tipoMedida, mudaValor = { novoValor ->
                        tipoMedida = novoValor })

                    InputCadastro2(titulo = "Valor Medida", placeholder = "", valorCampo = valorMedida, mudaValor = { novoValor ->
                        valorMedida = novoValor })
                }
            }

            Column( 
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImagemPasso2(onCadastroItemAnteriorClick = onCadastroItem2AnteriorClick)

                Button(
                    onClick = {
                        Toast.makeText(context, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                        onCadastroItemCadastrarClick()
                    },
                    modifier = Modifier
                        .height(55.dp)
                        .width(155.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Laranja
                    )
                ) {
                    Text(
                        text = "Cadastrar",
                        color = Black,
                        fontSize = 18.sp
                    )
                }
            }
        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 100.dp),
//            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
//        ) {
//            DownBarCadastroItemActivityScreen2()
//        }
    }
}

@Preview
@Composable
fun Cadastro2ScreenPreview() {
    CadastroItem2Screen(
        onCadastroItem2AnteriorClick = {},
        onCadastroItemCadastrarClick = {}
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBox() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Selecione") }
    val options = listOf("Unidade", "Kg", "Litros", "Caixas")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Campo do Select Box
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Tipo Medida") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                readOnly = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOption = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        // Outro campo de input como no exemplo "Valor Medida"
        OutlinedTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Valor Medida") }
        )
    }
}


@Composable
fun addReceita(){
    Button(
        modifier = Modifier
            .height(55.dp).padding(top = 10.dp),
        onClick = {
            /*TODO*/
        },
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GI_Laranja,
            contentColor = White
        )
    ) {
        Text(
            text = "+",
            color = White,
            fontSize = 22.sp
        )
    }
}

@Composable
fun AddrReceita(onAddClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = onAddClick,
        containerColor = GI_Laranja,
        contentColor = White,
        modifier = Modifier.width(70.dp).height(60.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Composable
fun InputCadastro2(
    titulo: String,
    placeholder: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(180.dp)
            .height(140.dp)

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
                        color = Color.Black
                    ),
                placeholder = {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                    )
                },
                singleLine = true

            )
        }

    }
}

//@Composable
//fun DownBarCadastroItemActivityScreen2() {
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
//                    Toast
//                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
//                        .show()
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
//                    Toast
//                        .makeText(context, "Ação 2", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.cart),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    Toast
//                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 4",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    Toast
//                        .makeText(context, "Ação 4", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//    }
//}

//@Preview
//@Composable
//fun DownBarCadastroItemActivityPreview2() {
//    DownBarCadastroItemActivityScreen2()
//}


@Composable
fun ImagemPasso2(
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
            onClick = { onCadastroItemAnteriorClick() }
        )
        RadioButton(
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
    }
}