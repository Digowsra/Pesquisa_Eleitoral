package com.example.pesquisaeleitoral.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Pesquisa::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pesquisaDao(): PesquisaDao
}