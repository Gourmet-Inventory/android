package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.R.drawable.cart
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.ui.theme.GI_Vermelho
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel

@Composable
fun DeleteCnfirmacaoScreen(
    viewModel: EstoqueViewModel,
    idItem: Long, // Adicione o ID do item a ser excluído
    onDeleteConfirmacaoConfirmarClick: () -> Unit,
    onDeleteConfirmacaoCancelarClick: () -> Unit
) {
    val context = LocalContext.current

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Tem certeza que deseja excluir este item?",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 50.dp, end = 40.dp, start = 40.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(
                    onClick = {
                        // Chame a função de deletar do ViewModel
                        viewModel.deletarEstoque(idItem)
                        onDeleteConfirmacaoConfirmarClick() // Isso pode ser usado para fechar a tela ou outra lógica
                    },
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Verde,
                        contentColor = colorResource(id = R.color.white)
                    )
                ) {
                    Text(
                        text = "Sim",
                        color = White,
                        fontSize = 18.sp
                    )
                }
                Button(
                    onClick = {
                        onDeleteConfirmacaoCancelarClick()
                        Toast
                            .makeText(context, "Ação cancelada", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Vermelho,
                        contentColor = colorResource(id = R.color.white)
                    )
                ) {
                    Text(
                        text = "Não",
                        color = White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun DeleteScreenPreview() {
//    DeleteCnfirmacaoScreen(
//        onDeleteConfirmacaoConfirmarClick = {},
//        onDeleteConfirmacaoCancelarClick = {},
//    )
//}
