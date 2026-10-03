package com.example.pesquisaeleitoral.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pesquisa_eleitoral.db"

            ).fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance

            instance
        }
    }
}