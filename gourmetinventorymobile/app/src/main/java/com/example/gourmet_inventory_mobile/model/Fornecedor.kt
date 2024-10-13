package com.example.gourmet_inventory_mobile.model

data class Fornecedor (
    val idFornecedor: Long,
    val nomeFornecedor: String,
    val cnpj: String,

    val cep: String,
    val logradouro: String,
    val complemento : String,
    val bairro: String,
    val localidade: String,
    val uf: String,

    val numeracaoLogradouro: String,
    val telefone : String,
    val categoria : String
)