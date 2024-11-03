package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import retrofit2.Response
import java.time.LocalDate

class PratoRepositoryImplLocal(): PratoRepository {

    override suspend fun getPratos(idEmpresa: Long): Response<List<Prato>> {
        return Response.success(
            List(10){
                Prato(
                    idPrato = it.toLong(),
                    nome = "Prato $it",
                    descricao = "Descrição $it",
                    preco = it.toDouble(),
                    alergicosRestricoes = listOf("Alergicos $it", "Restrições $it"),
                    categoria = "Categoria $it",
                    receitaPrato = listOf("Ingrediente $it"),
                    foto = "Foto $it",
                    URLAssinada = "URLAssinada $it"

                )

            }
        )
    }

}