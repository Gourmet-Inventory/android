package com.example.gourmet_inventory_mobile.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black

@Composable
fun SearchBox(searchText: String, mudaValorCampo: (String) -> Unit, modifier: Modifier) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
        modifier = modifier,
        placeholder = {
            Text(
                text = "Pesquisar",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Black,
                ),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Ícone de pesquisa"
            )
        },
        shape = RoundedCornerShape(5.dp),
    )
}