package com.example.pesquisaeleitoral.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Pesquisa::class,
        DadosEntrevistado::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pesquisaDao(): PesquisaDao

    abstract fun dadosEntrevistadoDao(): DadosEntrevistadoDao
}