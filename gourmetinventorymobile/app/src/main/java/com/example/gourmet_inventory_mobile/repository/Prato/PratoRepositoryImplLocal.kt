package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Ingrediente
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Prato
import retrofit2.Response

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
                    receitaPrato =
                        List(2){
                            Ingrediente(
                                nome = "Ingrediente $it",
                                tipoMedida = Medidas.LITRO.toString(),
                                valorMedida = it.toDouble(),
                                exibirConca = "ExibirConca $it"
                            )
                        },
                    foto = "Foto $it",
                    URLAssinada = "URLAssinada $it"
                )
            }
        )
    }

}