package com.example.gourmet_inventory_mobile.utils

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.White

sealed class BottomNavBar(
    val route: String,
    val icon: Int,
    val title: String
) {
    data object Cardapio : BottomNavBar(
        route = "cardapio",
        icon = R.drawable.book_icon,
        title = "Cardapio",
    )

    data object Comandas : BottomNavBar(
        route = "comandaList",
        icon = R.drawable.notes_icon,
        title = "Comandas",
    )

    data object PerfilView : BottomNavBar(
        route = "viewPerfil",
        icon = R.drawable.account_icon,
        title = "Perfil",
    )

    data object Estoque : BottomNavBar(
        route = "listaEstoque",
        icon = R.drawable.opened_box,
        title = "Estoque",
    )

    data object Fornecedores : BottomNavBar(
        route = "listaFornecedor",
        icon = R.drawable.fornecedores_db,
        title = "Fornecedores",
    )

    data object ListaCompras : BottomNavBar(
        route = "listaCompras",
        icon = R.drawable.cart,
        title = "Compras",
    )
}

@Composable
fun BottomBarGarcom(navController: NavController, onClick: (String) -> Unit) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Log.d("BottomBarGarcom", "currentRoute: $currentRoute")

    val items = listOf(
        BottomNavBar.Comandas,
        BottomNavBar.Cardapio,
        BottomNavBar.PerfilView,
    )

    NavigationBar(
        containerColor = GI_AzulMarinho,
        modifier = Modifier.height(75.dp)
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(25.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) Black else Color.White
                    )
                },
                label = { Text(text = item.title, color = Color.White) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        onClick(item.route)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSelected) White else Color.Transparent
                )
            )
        }
    }
}

@Composable
fun BottomBarGerente(navController: NavController, onClick: (String) -> Unit) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Log.d("BottomBarGerente", "currentRoute: $currentRoute")

    val items = listOf(
        BottomNavBar.Fornecedores,
        BottomNavBar.Estoque,
        BottomNavBar.ListaCompras,
        BottomNavBar.PerfilView,
    )

    NavigationBar(
        containerColor = GI_AzulMarinho,
        modifier = Modifier.height(75.dp),
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(30.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) Black else Color.White
                    )
                },
                label = { Text(text = item.title, color = Color.White) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        onClick(item.route)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSelected) White else Color.Transparent
                )
            )
        }
    }
}