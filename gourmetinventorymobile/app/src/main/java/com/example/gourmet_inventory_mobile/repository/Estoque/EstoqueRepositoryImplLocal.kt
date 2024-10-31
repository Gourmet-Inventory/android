package com.example.gourmet_inventory_mobile.repository.Estoque

import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.Medidas
import retrofit2.Response
import java.time.LocalDate

class EstoqueRepositoryImplLocal(): EstoqueRepository {
    override suspend fun obterEstoque(): Response<List<EstoqueConsulta>> {
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
                    dtaAviso = LocalDate.now()
                )
            }
        )
    }
}