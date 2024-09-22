package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.JostBold

@Composable
fun VizuFornScreen(
    onVizuFornVoltarClick: () -> Unit,
) {
    var cnpj by remember {
        mutableStateOf("08.792.981/0001-69")
    }
    var cep by remember {
        mutableStateOf("02050202")
    }
    var logradouro by remember {
        mutableStateOf("Rua Antônio Bento")
    }
    var complemento by remember {
        mutableStateOf("Padaria João")
    }
    var bairro by remember {
        mutableStateOf("Joaquim")
    }
    var localidade by remember {
        mutableStateOf("Curitiba")
    }
    var uf by remember {
        mutableStateOf("PR")
    }
    var numeracao by remember {
        mutableStateOf("12")
    }
    var telefone by remember {
        mutableStateOf("11989898989")
    }
    var categoria by remember {
        mutableStateOf("Frios")
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
                    onClick = { onVizuFornVoltarClick() },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }
    ) { padding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Kibon",
                        modifier = Modifier,
                        color = Black,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 35.sp
                        ),
                        fontFamily = JostBold
                    )
                }

                InfoForn(titulo = "CNPJ", valorCampo = cnpj, mudaValor = { novoValor ->
                    cnpj = novoValor
                })

                InfoForn(titulo = "CEP", valorCampo = cep, mudaValor = { novoValor ->
                    cep = novoValor
                })

                InfoForn(titulo = "Logradouro", valorCampo = logradouro, mudaValor = { novoValor ->
                    logradouro = novoValor
                })

                InfoForn(
                    titulo = "Complemento",
                    valorCampo = complemento,
                    mudaValor = { novoValor ->
                        complemento = novoValor
                    })

                InfoForn(titulo = "Bairro", valorCampo = bairro, mudaValor = { novoValor ->
                    bairro = novoValor
                })

                InfoForn(titulo = "Localidade", valorCampo = localidade, mudaValor = { novoValor ->
                    localidade = novoValor
                })

                InfoForn(titulo = "UF", valorCampo = uf, mudaValor = { novoValor ->
                    uf = novoValor
                })

                InfoForn(titulo = "Numeração", valorCampo = numeracao, mudaValor = { novoValor ->
                    numeracao = novoValor
                })

                InfoForn(titulo = "Telefone", valorCampo = telefone, mudaValor = { novoValor ->
                    telefone = novoValor
                })

                InfoForn(titulo = "Categoria", valorCampo = categoria, mudaValor = { novoValor ->
                    categoria = novoValor
                })

            }
        }
    }
}

@Preview
@Composable
fun VizuFornScreenPreview() {
    VizuFornScreen(
        onVizuFornVoltarClick = {},
    )
}


@Composable
fun InfoForn(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(320.dp)
            .height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp),
            text = "$titulo:",
            color = Black,
            fontSize = 21.sp,
            fontFamily = JostBold
        )

        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp),
            text = valorCampo,
            color = Black,
            fontSize = 21.sp
        )
    }
}