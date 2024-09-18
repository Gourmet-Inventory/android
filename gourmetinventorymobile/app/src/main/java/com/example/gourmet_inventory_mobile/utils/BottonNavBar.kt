package com.example.gourmet_inventory_mobile.utils

import com.example.gourmet_inventory_mobile.R

sealed class BottomNavBar(
    val route: String,
    val icon: Int,
    val title: String,
    val hasNews : Boolean,
    val badgeCount: Int? = null
) {
    data object Cardapio : BottomNavBar(
        route = "cardapio",
        icon = R.drawable.book_icon,
        title = "Cardapio",
        hasNews = false,
    )

    data object Comandas : BottomNavBar(
        route = "comandaList",
        icon = R.drawable.notes_icon,
        title = "Comandas",
        hasNews = false
    )

    data object PerfilView : BottomNavBar(
        route = "viewPerfil",
        icon = R.drawable.account_icon,
        title = "Perfil",
        hasNews = false
    )

    data object Estoque : BottomNavBar(
        route = "listaEstoque",
        icon = R.drawable.opened_box,
        title = "Estoque",
        hasNews = false
    )

    data object Fornecedores : BottomNavBar(
        route = "listaFornecedor",
        icon = R.drawable.fornecedores_db,
        title = "Fornecedores",
        hasNews = false
    )

    data object ListaCompras : BottomNavBar(
        route = "listaCompras",
        icon = R.drawable.account_icon,
        title = "Compras",
        hasNews = false
    )
}