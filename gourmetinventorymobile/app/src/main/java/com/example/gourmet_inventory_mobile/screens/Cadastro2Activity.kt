package com.example.gourmet_inventory_mobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.White

class Cadastro2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                Cadastro2Screen()

            }
        }
    }
}

@Composable
fun Cadastro2Screen() {
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
                .fillMaxSize()
                .padding(top = 60.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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
                        fontSize = 35.sp
                    )
                )
            }
            Row (
                modifier = Modifier
                    .width(350.dp)
                    .height(120.dp),
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
                        .width(180.dp)
                )
            }
            Row {
                InputCadastro2(titulo = "Tipo Medida", placeholder = "", valorCampo = tipoMedida, mudaValor = { novoValor ->
                    tipoMedida = novoValor })

                InputCadastro2(titulo = "Valor Medida", placeholder = "", valorCampo = valorMedida, mudaValor = { novoValor ->
                    valorMedida = novoValor })
            }

            Row {
                InputCadastro2(titulo = "Data Cadastro", placeholder = "dd/mm/aaaa", valorCampo = dataCadastro, mudaValor = { novoValor ->
                    dataCadastro = novoValor })

                InputCadastro2(titulo = "Data Aviso", placeholder = "dd/mm/aaaa", valorCampo = dataAviso, mudaValor = { novoValor ->
                    dataAviso = novoValor })
            }

            Column( 
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                ImagemPasso2()

                Button(
                    onClick = {
                        Toast.makeText(context, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .height(55.dp)
                        .width(155.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Orange,
                        contentColor = colorResource(id = R.color.white)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ) {
            DownBarCadastroScreen2()
        }
    }
}

@Preview
@Composable
fun Cadastro2ScreenPreview() {
    Cadastro2Screen()
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
            .height(160.dp)

    ){
        Column(
            modifier = Modifier
                .size(width = 350.dp, height = 100.dp),
            
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
                }

            )
        }

    }
}

@Composable
fun DownBarCadastroScreen2() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(80.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.fornecedores_db),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
                        .show()
                }
        )
//        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.opened_box),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 2", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.carrinho_de_compraspng),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 4",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 4", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

@Preview
@Composable
fun DownBarDeleteScreenPreview2() {
    DownBarCadastroScreen2()
}


@Composable
fun ImagemPasso2() {
    Image(
        painter = painterResource(id = R.drawable.bolinha2),
        contentDescription = "Bolinha",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(30.dp)
            .padding(bottom = 15.dp)
    )
}