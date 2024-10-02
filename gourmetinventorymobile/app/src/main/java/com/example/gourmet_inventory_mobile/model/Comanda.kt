package com.example.gourmet_inventory_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Comanda(
    val mesa: String,
    val nomeCliente: String,
    val nomeComanda: String,
    val garcom : User,
)