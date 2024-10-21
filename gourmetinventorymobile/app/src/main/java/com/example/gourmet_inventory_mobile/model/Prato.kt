package com.example.gourmet_inventory_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Prato(val nome: String, val preco: Double, val rendimento: String)

data class PratoResponse(val pratos: List<Prato>)
