package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde

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
    onItemEstoqueViewVoltarClick: () -> Unit,
    onItemEstoqueViewEditarClick: () -> Unit,
    onItemEstoqueViewExcluirClick: () -> Unit,
    onItemEstoqueViewAcao1Click: () -> Unit,
    onItemEstoqueViewAcao2Click: () -> Unit,
    onItemEstoqueViewAcao3Click: () -> Unit,
    onItemEstoqueViewAcao4Click: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
//        .padding(16.dp)
    ) {
        val context = LocalContext.current

        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onItemEstoqueViewVoltarClick()
                }) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowLeft,
//                painter = painterResource(id = R.drawable.back),
                        contentDescription = "Voltar",
                        Modifier.size(44.dp),
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Molho de Tomate",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        bottom = 40.dp
                    )
                )
            }
            Column() {
                InfoItem("Lote:", "Lote 1")
                InfoItem("Categoria:", "Molhos", topPadding = 31.dp)
                InfoItem("Local Armazenamento:", "Geladeira", topPadding = 34.dp)
                InfoItem("Quantidade Unitária:", "3", topPadding = 36.dp)
                InfoItem("Tipo Medida:", "GRAMAS", topPadding = 30.dp)
                InfoItem("Valor medida:", "500", topPadding = 41.dp)
                InfoItem("Data Cadastro:", "20/05/2024", topPadding = 45.dp)
                InfoItem("Data Aviso:", "20/05/2024", topPadding = 34.dp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        onItemEstoqueViewExcluirClick()
                        Toast.makeText(context, "Item Excluído com sucesso", Toast.LENGTH_SHORT)
                            .show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFED5656),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                        contentDescription = "Excluir",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Excluir")
                }

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
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Editar")
                }
            }

        }

        // Barra Inferior Fixa
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ) {
            ItemDownBar(
                onItemViewAcao1Click = onItemEstoqueViewAcao1Click,
                onItemViewAcao2Click = onItemEstoqueViewAcao2Click,
                onItemViewAcao3Click = onItemEstoqueViewAcao3Click,
                onItemViewAcao4Click = onItemEstoqueViewAcao4Click
            )
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, topPadding: Dp = 0.dp) {
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

@Composable
fun ItemDownBar(
    onItemViewAcao1Click: () -> Unit,
    onItemViewAcao2Click: () -> Unit,
    onItemViewAcao3Click: () -> Unit,
    onItemViewAcao4Click: () -> Unit,
) {
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
                    onItemViewAcao1Click()
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
                    onItemViewAcao2Click()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onItemViewAcao3Click()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 4",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onItemViewAcao4Click()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ItemEstoqueScreen(
        onItemEstoqueViewVoltarClick = {},
        onItemEstoqueViewEditarClick = {},
        onItemEstoqueViewExcluirClick = {},
        onItemEstoqueViewAcao1Click = {},
        onItemEstoqueViewAcao2Click = {},
        onItemEstoqueViewAcao3Click = {},
        onItemEstoqueViewAcao4Click = {}
    )
}
