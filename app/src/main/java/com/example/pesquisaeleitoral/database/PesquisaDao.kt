package com.example.pesquisaeleitoral.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PesquisaDao {

    @Insert
    suspend fun inserir(pesquisa: Pesquisa): Long

    @Query("SELECT * FROM pesquisas")
    suspend fun listarTodas(): List<Pesquisa>

    @Query("""
        UPDATE pesquisas
        SET candidatoEstimulado = :candidato
        WHERE id = :id
    """)
    suspend fun salvarCandidatoEstimulado(
        id: Long,
        candidato: String
    )
}