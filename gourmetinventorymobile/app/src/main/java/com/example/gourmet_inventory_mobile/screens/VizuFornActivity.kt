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
import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.model.User
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.JostBold

@Composable
fun VizuFornScreen(
    fornecedor: Fornecedor,
    onVizuFornVoltarClick: () -> Unit
) {

    val context = LocalContext.current
    val resources = context.resources
    var currentForn: Fornecedor? by remember { mutableStateOf(null) }

    var nomeFornecedor by remember {
        mutableStateOf("")
    }
    var cnpj by remember {
        mutableStateOf("")
    }
    var cep by remember {
        mutableStateOf("")
    }
    var logradouro by remember {
        mutableStateOf("")
    }
    var complemento by remember {
        mutableStateOf("")
    }
    var bairro by remember {
        mutableStateOf("")
    }
    var localidade by remember {
        mutableStateOf("")
    }
    var uf by remember {
        mutableStateOf("")
    }
    var numeracao by remember {
        mutableStateOf("")
    }
    var telefone by remember {
        mutableStateOf("")
    }
    var categoria by remember {
        mutableStateOf("")
    }

    nomeFornecedor = fornecedor?.nomeFornecedor ?: ""
    cnpj = fornecedor?.cnpj ?: ""
    cnpj = fornecedor?.cnpj ?: ""
    cep = fornecedor?.cep ?: ""
    logradouro = fornecedor?.logradouro ?: ""
    complemento = fornecedor?.complemento ?: ""
    bairro = fornecedor?.bairro ?: ""
    localidade = fornecedor?.localidade ?: ""
    uf = fornecedor?.uf ?: ""
    numeracao = fornecedor?.numeracaoLogradouro ?: ""
    telefone = fornecedor?.telefone ?: ""
    categoria = fornecedor?.categoria ?: ""

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
                        text = fornecedor.nomeFornecedor,
                        modifier = Modifier,
                        color = Black,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 35.sp
                        ),
                        fontFamily = JostBold
                    )
                }

                cnpj?.let {
                    InfoForn(titulo = "CNPJ", valorCampo = it)

                }

                cep?.let {
                    InfoForn(titulo = "CEP", valorCampo = it)
                }

                logradouro?.let {
                    InfoForn(titulo = "Logradouro", valorCampo = it)
                }

                complemento?.let {
                    InfoForn(titulo = "Complemento", valorCampo = it)
                }

                bairro?.let {
                    InfoForn(titulo = "Bairro", valorCampo = it)
                }

                localidade?.let {
                    InfoForn(titulo = "Localidade", valorCampo = it)
                }

                uf?.let {
                    InfoForn(titulo = "UF", valorCampo = it)
                }

                numeracao?.let {
                    InfoForn(titulo = "Numeração", valorCampo = it)
                }

                telefone?.let {
                    InfoForn(titulo = "Telefone", valorCampo = it)
                }

                categoria?.let {
                    InfoForn(titulo = "Categoria", valorCampo = it)
                }


            }
        }
    }
}

@Preview
@Composable
fun VizuFornScreenPreview() {
    VizuFornScreen(
        fornecedor = Fornecedor(
            idFornecedor =  1,
            nomeFornecedor = "Fornecedor 1",
            cnpj = "CNPJ 1",
            cep =  "string",
            logradouro = "string",
            complemento= "string",
            bairro = "string",
            localidade ="string",
            uf ="string",
            numeracaoLogradouro = "string",
            telefone = "string",
            categoria= "string"
        ),
        onVizuFornVoltarClick = {},
    )
}


@Composable
fun InfoForn(
    titulo: String,
    valorCampo: String
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