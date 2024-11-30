package com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import java.time.format.DateTimeFormatter

//class ItemEstoque : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                ItemEstoque()
//            }
//        }
//    }
//}

@Composable
fun ItemEstoqueScreen(
    estoqueConsulta: EstoqueItemDiscriminator.Industrializado,
    onItemEstoqueClick: (String) -> Unit,
    onItemEstoqueViewEditarClick: () -> Unit,
    onItemEstoqueViewExcluirClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onItemEstoqueClick("Voltar") },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier
//                            .background(color = White, shape = RoundedCornerShape(50))
                            .fillMaxSize(),
//                            .border(1.dp, Color.Black, shape = RoundedCornerShape(50)),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }
    ) { padding ->

        var lote by remember { mutableStateOf("") }
        var categoria by remember { mutableStateOf("") }
        var marca by remember { mutableStateOf("") }
        var localArmazenamento by remember { mutableStateOf("") }
        var quantidadeUnitaria by remember { mutableStateOf("") }
        var tipoMedida by remember { mutableStateOf("") }
        var valorMedida by remember { mutableStateOf("") }
        var valorTotal by remember { mutableStateOf("") }
        var dataCadastro by remember { mutableStateOf("") }
        var dataAviso by remember { mutableStateOf("") }

        if (estoqueConsulta != null) {
            lote = estoqueConsulta.lote
            categoria = estoqueConsulta.categoria.toString()
            marca = estoqueConsulta.marca
            localArmazenamento = estoqueConsulta.localArmazenamento
            quantidadeUnitaria = estoqueConsulta.unitario.toString()
            tipoMedida = estoqueConsulta.tipoMedida.nomeExibicao
            valorMedida = estoqueConsulta.valorMedida.toString()
            valorTotal = estoqueConsulta.valorTotal.toString()
            dataCadastro =
                estoqueConsulta.dtaCadastro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            dataAviso = estoqueConsulta.dtaAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }


        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            val context = LocalContext.current

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = estoqueConsulta.nome,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            bottom = 20.dp
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .height(600.dp)
                        .padding(end = 0.dp, top = 20.dp)
                ) {
                    DrawScrollableView(
                        modifier = Modifier
                            .fillMaxHeight(),
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp, end = 0.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                estoqueConsulta?.let {
                                    InfoItemEstoque("Lote", lote)
                                    InfoItemEstoque("Categoria", categoria)
                                    InfoItemEstoque("Marca", marca)
                                    InfoItemEstoque("Local Armazenamento", localArmazenamento)
                                    InfoItemEstoque("Quantidade Unitária", quantidadeUnitaria)
                                    InfoItemEstoque("Tipo Medida", tipoMedida)
                                    InfoItemEstoque("Valor Medida", valorMedida)
                                    InfoItemEstoque("Valor Total", valorTotal)
                                    InfoItemEstoque("Data Cadastro", dataCadastro)
                                    InfoItemEstoque("Data Aviso", dataAviso)
                                }
                            }
                        }
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onItemEstoqueViewEditarClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Verde,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Editar")
                    }
                    Button(
                        onClick = {
                            onItemEstoqueViewExcluirClick("deleteConfirmacao/${estoqueConsulta.idItem}")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFED5656),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                            contentDescription = "Excluir",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Excluir")
                    }
                }

            }
        }
    }
}

//@Composable
//fun InfoItem(label: String, value: String, topPadding: Dp = 0.dp) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = topPadding, start = 20.dp, end = 20.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
//        Text(text = value, fontSize = 20.sp)
//
//    }
//}

@Composable
fun InfoItemEstoque(
    titulo: String,
    valorCampo: String
) {
    Column(
//        horizontalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(330.dp)
            .height(90.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 0.dp)
                .height(30.dp),
            text = "$titulo:",
            color = Black,
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            text = valorCampo,
            color = Black,
            fontSize = 20.sp,
            fontFamily = JostBold
        )

//        Coloque um espaço aqui
        Spacer(modifier = Modifier.height(5.dp))

        if (titulo != "Data Aviso") {
            Divider(
                color = Color.Gray,
                thickness = 1.dp, // Espessura da linha
                modifier = Modifier
                    .padding(horizontal = 0.dp)
            )

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemPreview() {
//    ItemEstoqueScreen(
//        onItemEstoqueClick = {},
//        onItemEstoqueViewEditarClick = {},
//        onItemEstoqueViewExcluirClick = {},
//        estoqueConsulta = {}
//    )
//}
