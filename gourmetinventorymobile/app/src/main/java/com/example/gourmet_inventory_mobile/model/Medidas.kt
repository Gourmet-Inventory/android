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
    UNIDADE("unid.", "Unidade");

    companion object {
        fun fromDisplayName(displayName: String): Medidas {
            return values().find { it.nomeExibicao.equals(displayName, ignoreCase = true) }
                ?: throw IllegalArgumentException("Medida inválida: $displayName")
        }
    }
    fun String.toMedidas(): Medidas? {
        return try {
            Medidas.valueOf(this.uppercase()) // Tenta encontrar o enum equivalente
        } catch (e: IllegalArgumentException) {
            null // Retorna null se não encontrar
        }
    }
}