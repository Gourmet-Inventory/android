package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Ingrediente
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import retrofit2.Response
import java.time.LocalDate

class ComandaRepositoryImplLocal(): ComandaRepository {
    override suspend fun getAllComandas(): Response<List<Comanda>> {
        return Response.success(
            List(10){
//                for (i in 0..10) {
//                    if (i >= 0 && i <= 5){
//
//                    }
//                }
                Comanda(
                    id = it.toLong(),
                    idGarcom = it.toLong(),
                    titulo = "Comanda $it",
                    mesa = "Mesa $it",
                    itens = List(10){
                        Prato(
                            idPrato = it.toLong(),
                            nome = "Prato $it",
                            descricao = "Descrição $it",
                            preco = it.toDouble(),
                            alergicosRestricoes = listOf("Alergicos $it", "Restrições $it"),
                            categoria = "Categoria $it",
                            receitaPrato = List(2){
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
                    },
                     status = "Status $it",
                     total = it.toDouble()
                )
            }
        )
    }

    override suspend fun getLastComanda(): Response<Comanda> {
        return Response.success(
            Comanda(
                id = 1,
                idGarcom = 1,
                titulo = "Comanda 1",
                mesa = "Mesa 1",
                itens = List(10){
                    Prato(
                        idPrato = it.toLong(),
                        nome = "Prato $it",
                        descricao = "Descrição $it",
                        preco = it.toDouble(),
                        alergicosRestricoes = listOf("Alergicos $it", "Restrições $it"),
                        categoria = "Categoria $it",
                        receitaPrato = List(2){
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
                },
                status = "Status 1",
                total = 1.0
            )
        )
    }

    override suspend fun createComanda(comanda: Comanda): Response<Comanda> {
        return Response.success(Comanda(
            id = 1,
            idGarcom = 1,
            titulo = "Comanda 1",
            mesa = "Mesa 1",
            itens = List(5){
                Prato(
                    idPrato = it.toLong(),
                    nome = "Prato $it",
                    descricao = "Descrição $it",
                    preco = it.toDouble(),
                    alergicosRestricoes = listOf("Alergicos $it", "Restrições $it"),
                    categoria = "Categoria $it",
                    receitaPrato = List(2){
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
            },
            status = "Status 1",
            total = 1.0
        ))
    }
}