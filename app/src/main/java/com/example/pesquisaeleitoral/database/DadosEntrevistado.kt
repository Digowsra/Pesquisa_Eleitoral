package com.example.pesquisaeleitoral.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dados_entrevistados")
data class DadosEntrevistado(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,

    val celular: String
)