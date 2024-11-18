package com.example.gourmet_inventory_mobile.screens.Fornecedor

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.model.Fornecedor
import androidx.compose.material3.Divider
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.JostLight

@Composable
fun VizuFornScreen(
    fornecedor: Fornecedor,
    onVizuFornVoltarClick: () -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources

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
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
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
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 60.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = fornecedor.nomeFornecedor,
                        color = Black,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 35.sp),
                        fontFamily = JostBold
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(700.dp)
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    fornecedor.cnpj?.let {
                        item { InfoForn(titulo = "CNPJ", valorCampo = it) }
                    }

                    fornecedor.cep?.let {
                        item { InfoForn(titulo = "CEP", valorCampo = it) }
                    }

                    fornecedor.logradouro?.let {
                        item { InfoForn(titulo = "Logradouro", valorCampo = it) }
                    }

                    fornecedor.complemento?.let {
                        item { InfoForn(titulo = "Complemento", valorCampo = it) }
                    }

                    fornecedor.bairro?.let {
                        item { InfoForn(titulo = "Bairro", valorCampo = it) }
                    }

                    fornecedor.localidade?.let {
                        item { InfoForn(titulo = "Localidade", valorCampo = it) }
                    }

                    fornecedor.uf?.let {
                        item { InfoForn(titulo = "UF", valorCampo = it) }
                    }

                    fornecedor.numeracaoLogradouro?.let {
                        item { InfoForn(titulo = "Numeração", valorCampo = it) }
                    }

                    fornecedor.telefone?.let {
                        item { InfoForn(titulo = "Telefone", valorCampo = it) }
                    }

                    fornecedor.categoria?.let {
                        item { InfoForn(titulo = "Categoria", valorCampo = it) }
                    }
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
            idFornecedor = 1,
            nomeFornecedor = "Fornecedor 1",
            cnpj = "CNPJ 1",
            cep = "string",
            logradouro = "string",
            complemento = "string",
            bairro = "string",
            localidade = "string",
            uf = "string",
            numeracaoLogradouro = "string",
            telefone = "string",
            categoria = "string"
        ),
        onVizuFornVoltarClick = {},
    )
}


@Composable
fun InfoForn(
    titulo: String,
    valorCampo: String
) {
    Column(
//        horizontalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(320.dp)
            .height(90.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp),
            text = "$titulo:",
            color = Black,
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .height(35.dp),
            text = valorCampo,
            color = Black,
            fontSize = 26.sp,
            fontFamily = JostBold
        )

//        Coloque um espaço aqui
        Spacer(modifier = Modifier.height(5.dp))

        Divider(
            color = Color.Gray,
            thickness = 1.dp, // Espessura da linha
            modifier = Modifier
                .padding(horizontal = 0.dp)
        )
    }
}