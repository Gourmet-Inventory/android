package com.example.gourmet_inventory_mobile.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

@Composable
fun LoadingItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                GI_AzulMarinho.copy(alpha = 0.2f),
                RoundedCornerShape(8.dp)
            ),
//            .border(2.dp,
//                GI_CinzaClaro, RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
//                .background(color = GI_Cinza)
//                .border(2.dp,
//                    GI_CinzaClaro, RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(
//                        GI_CinzaClaro,
//                        RoundedCornerShape(
//                            bottomEnd = 0.dp,
//                            bottomStart = 0.dp,
//                            topEnd = 8.dp,
//                            topStart = 8.dp,
//                        )
//                    )
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "",
                    fontSize = 20.sp,
                    fontFamily = JostBold,
                    color = White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "",
                    fontSize = 16.sp
                )
                Text(
                    text = "",
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingListPreview() {
    LoadingList()
}

@Composable
fun LoadingList() {
    Column {
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
        LoadingItem()
    }
}