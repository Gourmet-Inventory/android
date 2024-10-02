package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.ui.theme.White

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
    onItemEstoqueClick: (String) -> Unit,
    onItemEstoqueViewEditarClick: () -> Unit,
    onItemEstoqueViewExcluirClick: () -> Unit,
) {
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
                    InfoItem("Valor total:", "1500", topPadding = 41.dp)
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

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ItemEstoqueScreen(
        onItemEstoqueClick = {},
        onItemEstoqueViewEditarClick = {},
        onItemEstoqueViewExcluirClick = {}
    )
}
