package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import com.example.gourmet_inventory_mobile.repository.ListaCompras.ListaComprasRepository
import retrofit2.Response
import java.time.LocalDate

class ListaComprasRepositoryImplLocal(): ListaComprasRepository {
    override suspend fun obterListaCompras(idEmpresa: Long): Response<List<ItemListaCompras>> {
        return Response.success(
            List(5){
                ItemListaCompras(
                    idItemLista = it.toLong(),
                    nome = "Nome $it",
                    qtdMedia = it,
                    estoqueIngrediente = Estoque(
                        idItem = it.toLong(),
                        lote = "Lote $it",
                        empresa = Empresa(
                            idEmpresa = it.toLong(),
                            nomeFantasia = "Empresa"),
                        nome = "Nome $it",
                        categoria = "Categoria $it",
                        tipoMedida = Medidas.GRAMAS,
                        unitario = it,
                        valorMedida = it.toDouble(),
                        valorTotal = it.toDouble(),
                        localArmazenamento = "LocalArmazenamento $it",
                        dtaCadastro = LocalDate.now(),
                        dtaAviso = LocalDate.now(),
                        marca = "Marca $it"
                    )

                )
            }
        )
    }

}