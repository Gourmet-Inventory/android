package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde

//class ItemEstoquManipuladoActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            GourmetinventorymobileTheme {
//                ItemEstoqueManipuladoScreen(
//                    onItemEstoqueClick = {},
//                    onItemEstoqueViewEditarClick = { /*TODO*/ }) {
//
//                }
//            }
//
//        }
//    }
//}

@Composable
fun ItemEstoqueManipuladoScreen(
    onItemEstoqueClick: (String) -> Unit,
    onItemEstoqueViewEditarClick: () -> Unit,
    onItemEstoqueViewExcluirClick: () -> Unit,
) {
    val receita = listOf(
        Receita(50.0, "GRAMAS", "TOMATE"),
        Receita(50.0, "GRAMAS", "TOMATE"),
        Receita(50.0, "GRAMAS", "TOMATE"),
        Receita(50.0, "GRAMAS", "TOMATE"),
        Receita(50.0, "GRAMAS", "TOMATE"),
        Receita(50.0, "GRAMAS", "TOMATE")
    )
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val context = LocalContext.current

            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Molho de Tomate",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                    )
                    Text(
                        text = "MANIPULADO",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(
                            bottom = 50.dp
                        )
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .height(250.dp)
                ) {
                    item { InfoItemManipulado("Lote:", "Lote 1") }
                    item { InfoItemManipulado("Categoria:", "Molhos", topPadding = 22.dp) }
                    item { InfoItemManipulado("Marca:", "Quero", topPadding = 22.dp) }
                    item {
                        InfoItemManipulado(
                            "Local Armazenamento:",
                            "Geladeira",
                            topPadding = 22.dp
                        )
                    }
                    item { InfoItemManipulado("Quantidade Unitária:", "3", topPadding = 22.dp) }
                    item { InfoItemManipulado("Tipo Medida:", "GRAMAS", topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Medida:", "500", topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Total:", "1500", topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Cadastro:", "20/05/2024", topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Aviso:", "20/05/2024", topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Medida:", "500", topPadding = 22.dp) }
                    item { InfoItemManipulado("Valor Total:", "1500", topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Cadastro:", "20/05/2024", topPadding = 22.dp) }
                    item { InfoItemManipulado("Data Aviso:", "20/05/2024", topPadding = 22.dp) }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Receita:", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(170.dp)
                        .border(1.dp, color = Color.Black)
                ) {
                    ItensReceita(receitas = receita)
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp, end = 15.dp),
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
                            imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Editar")
                    }
                    Button(
                        onClick = {
                            onItemEstoqueViewExcluirClick()
                            Toast.makeText(
                                context,
                                "Item Excluído com sucesso",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFED5656),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(start = 5.dp),
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

@Composable
fun InfoItemManipulado(label: String, value: String, topPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp)

    }


}


data class Receita(val valorMedida: Double, val tipoMedida: String, val nome: String)

@Composable
fun ItensReceita(receitas: List<Receita>) {
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .fillMaxSize()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(receitas) { receita ->
            ItemReceita(receita = receita)
        }
    }
}


@Composable
fun ItemReceita(receita: Receita) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${receita.valorMedida} ${receita.tipoMedida} - ${receita.nome}",
            fontSize = 19.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemManipuladoPreview() {
    ItemEstoqueManipuladoScreen(
        onItemEstoqueClick = {},
        onItemEstoqueViewEditarClick = {},
        onItemEstoqueViewExcluirClick = {}
    )
}

