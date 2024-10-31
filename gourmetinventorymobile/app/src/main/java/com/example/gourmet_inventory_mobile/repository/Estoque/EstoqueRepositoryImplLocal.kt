package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueCriacao
import retrofit2.Response
import java.time.LocalDate

class EstoqueRepositoryImplLocal(): EstoqueRepository {
    override suspend fun getEstoque(idEmpresa: Long): Response<List<EstoqueConsulta>> {
        return Response.success(
            List(18){
                EstoqueConsulta(
                    idItem = it.toLong(),
                    empresa = Empresa(
                        idEmpresa = it.toLong(),
                        nomeFantasia = "Empresa $it"
                    ),
                    manipulado = true,
                    lote = "Lote $it",
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
            }
        )    }

    override suspend fun createEstoque(
        idEmpresa: Long,
        estoque: EstoqueCriacao
    ): Response<EstoqueConsulta> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEstoque(
        idEmpresa: Long,
        estoque: Estoque
    ): Response<EstoqueConsulta> {
        TODO("Not yet implemented")
    }
}