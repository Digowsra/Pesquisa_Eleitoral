package com.example.pesquisaeleitoral.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DadosEntrevistadoDao {

    @Insert
    suspend fun inserir(dados: DadosEntrevistado): Long

    @Query("SELECT * FROM dados_entrevistados")
    suspend fun listarTodos(): List<DadosEntrevistado>
}