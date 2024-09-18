package com.example.gourmet_inventory_mobile.utils

import com.example.gourmet_inventory_mobile.R

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
        icon = R.drawable.account_icon,
        title = "Compras",
    )
}