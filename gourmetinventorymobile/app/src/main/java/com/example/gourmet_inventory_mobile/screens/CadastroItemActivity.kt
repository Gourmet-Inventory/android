package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.R.drawable.cart
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.White

//class CadastroActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                CadastroScreen()
//
//            }
//        }
//    }
//}

@Composable
fun CadastroItemScreen(
    onCadastroItemVoltarClick: () -> Unit = {},
    onCadastroItemProximoClick: () -> Unit = {}
) {
    var nome by remember {
        mutableStateOf("")
    }
    var lote by remember {
        mutableStateOf("")
    }
    var categoria by remember {
        mutableStateOf("")
    }
    var localArmazenamento by remember {
        mutableStateOf("")
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column(
            modifier = Modifier
                .fillMaxSize() ,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onCadastroItemVoltarClick()
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
                    .height(105.dp),
//                    .padding(top = 45.dp, start = 26.dp, end = 26.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Cadastrar Item:",
                    modifier = Modifier,
                    color = Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 35.sp
                    )
                )
            }
            InputCadastro(titulo = "Nome",valorCampo = nome, mudaValor = { novoValor ->
                        nome = novoValor })

            InputCadastro(titulo = "Lote",valorCampo = lote, mudaValor = { novoValor ->
                lote = novoValor })

            InputCadastro(titulo = "Categoria",valorCampo = categoria, mudaValor = { novoValor ->
                categoria = novoValor })

            InputCadastro(titulo = "Local Armazenamento",valorCampo = localArmazenamento, mudaValor = { novoValor ->
                localArmazenamento = novoValor })

            ImagemPasso1(onCadastroItemProximoClick = onCadastroItemProximoClick)

            Button(
                onClick = {
                    onCadastroItemProximoClick()
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
fun CadastroScreenPreview() {
    CadastroItemScreen()
}


@Composable
fun InputCadastro(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

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
                    .height(50.dp)
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
fun ImagemPasso1(
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
            selected = selectedOptionIndex == 1,
            onClick = { selectedOptionIndex = 1 }
        )
        RadioButton(
            selected = selectedOptionIndex == 0,
            onClick = { onCadastroItemProximoClick() }
        )
    }
}