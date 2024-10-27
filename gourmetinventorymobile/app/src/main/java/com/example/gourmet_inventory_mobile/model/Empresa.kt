package com.example.gourmet_inventory_mobile.model

import kotlinx.serialization.Serializable

@Serializable
data class Empresa (
    val idEmpresa: Long,
    val nomeFantasia: String,
)