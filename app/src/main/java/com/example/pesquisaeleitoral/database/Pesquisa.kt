package com.example.pesquisaeleitoral.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pesquisas")
data class Pesquisa(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val candidatoEspontaneo: String,

    val candidatoEstimulado: String? = null
)