package com.example.pesquisaeleitoral

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pesquisaeleitoral.database.DatabaseProvider
import kotlinx.coroutines.launch

class EntrevistadosActivity : AppCompatActivity() {

    private lateinit var tvTotalEntrevistados: TextView
    private lateinit var tvListaEntrevistados: TextView
    private lateinit var btVoltarEntrevistados: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_entrevistados)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        tvTotalEntrevistados =
            findViewById(R.id.tvTotalEntrevistados)

        tvListaEntrevistados =
            findViewById(R.id.tvListaEntrevistados)

        btVoltarEntrevistados =
            findViewById(R.id.btVoltarEntrevistados)

        btVoltarEntrevistados.setOnClickListener {
            finish()
        }

        carregarEntrevistados()
    }

    private fun carregarEntrevistados() {

        lifecycleScope.launch {

            val entrevistados = DatabaseProvider
                .getDatabase(this@EntrevistadosActivity)
                .dadosEntrevistadoDao()
                .listarTodos()

            tvTotalEntrevistados.text =
                "Total de cadastros: ${entrevistados.size}"

            if (entrevistados.isEmpty()) {

                tvListaEntrevistados.text =
                    "Nenhum entrevistado cadastrado."

                return@launch
            }

            val texto = entrevistados.joinToString(
                separator = "\n────────────────────\n\n"
            ) { entrevistado ->

                val nome = entrevistado.nome
                    .ifBlank { "Não informado" }

                val celular = entrevistado.celular
                    .ifBlank { "Não informado" }

                "Nome: $nome\nCelular: $celular"
            }

            tvListaEntrevistados.text = texto
        }
    }
}