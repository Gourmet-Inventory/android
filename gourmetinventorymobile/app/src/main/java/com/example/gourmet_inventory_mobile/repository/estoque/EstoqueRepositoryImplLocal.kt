package com.example.gourmet_inventory_mobile.repository.estoque

import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueCriacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.industrializado.EstoqueIngredienteAtualizacaoDto
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoAtualizacao
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoConsulta
import com.example.gourmet_inventory_mobile.model.estoque.manipulado.EstoqueManipuladoCriacao
import retrofit2.Response
import java.time.LocalDate

class EstoqueRepositoryImplLocal(): EstoqueRepository {
    override suspend fun getEstoque(idEmpresa: Long): Response<List<Map<String, Any>>> {
        val estoqueItems = listOf(
            mapOf(
                "idItem" to 1L,
                "manipulado" to false,
                "lote" to "Lote 1",
                "nome" to "Estoque 1",
                "categoria" to CategoriaEstoque.MOLHOS_E_CONDIMENTOS,
                "tipoMedida" to Medidas.GRAMAS,
                "unitario" to 1,
                "valorMedida" to 500.0,
                "valorTotal" to 1500.0,
                "localArmazenamento" to "Geladeira",
                "dtaCadastro" to LocalDate.now(),
                "dtaAviso" to LocalDate.now().plusDays(1),
                "marca" to "Marca A"
            )
        )
        return Response.success(estoqueItems)
    }

    override suspend fun createEstoque(
        idEmpresa: Long,
        estoque: EstoqueCriacaoDto
    ): Response<EstoqueConsulta> {
        TODO("Not yet implemented")
    }

    override suspend fun createEstoqueManipulado(
        idEmpresa: Long,
        estoque: EstoqueManipuladoCriacao
    ): Response<EstoqueManipuladoConsulta> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEstoque(
        idEmpresa: Long,
        estoque: EstoqueIngredienteAtualizacaoDto
    ): Response<EstoqueConsulta> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEstoqueManipulado(
        idEstoque: Long,
        estoque: EstoqueManipuladoAtualizacao
    ): Response<EstoqueManipuladoConsulta> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEstoque(
        id: Long
    ): Response<Unit> {
        TODO("Not yet implemented")
    }
}