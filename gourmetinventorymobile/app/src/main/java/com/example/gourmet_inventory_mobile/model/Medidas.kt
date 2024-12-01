package com.example.gourmet_inventory_mobile.model

enum class Medidas(val abreviacao: String, val nomeExibicao: String) {
    COLHER_DE_SOPA("c.s.", "Colher de Sopa"),
    COLHER_DE_CHA("c.c.", "Colher de Chá"),
    XICARA("xíc.", "Xícara"),
    GRAMAS("g", "Gramas"),
    QUILOGRAMA("kg", "Quilograma"),
    MILILITROS("ml", "Mililitros"),
    LITRO("l", "Litro"),
    A_GOSTO("a gosto", "A Gosto"),
    PITADA("pit.", "Pitada"),
    UNIDADE("unid.", "Unidade")
}