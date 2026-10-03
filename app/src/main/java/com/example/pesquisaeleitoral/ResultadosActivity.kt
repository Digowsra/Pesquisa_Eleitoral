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

class ResultadosActivity : AppCompatActivity() {

    private lateinit var tvTotalPesquisas: TextView
    private lateinit var tvResultadoEspontanea: TextView
    private lateinit var tvResultadoEstimulada: TextView
    private lateinit var tvResultadoProblemas: TextView
    private lateinit var btVoltarResultados: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_resultados)

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

        tvTotalPesquisas = findViewById(R.id.tvTotalPesquisas)
        tvResultadoEspontanea = findViewById(R.id.tvResultadoEspontanea)
        tvResultadoEstimulada = findViewById(R.id.tvResultadoEstimulada)
        tvResultadoProblemas = findViewById(R.id.tvResultadoProblemas)
        btVoltarResultados = findViewById(R.id.btVoltarResultados)

        btVoltarResultados.setOnClickListener {
            finish()
        }

        carregarResultados()
    }

    private fun carregarResultados() {

        lifecycleScope.launch {

            val pesquisas = DatabaseProvider
                .getDatabase(this@ResultadosActivity)
                .pesquisaDao()
                .listarTodas()

            tvTotalPesquisas.text =
                "Total de pesquisas: ${pesquisas.size}"

            // ----------------------------
            // PESQUISA ESPONTÂNEA
            // ----------------------------

            val espontaneos = pesquisas
                .map { it.candidatoEspontaneo.trim() }
                .filter { it.isNotEmpty() }
                .groupingBy { it }
                .eachCount()
                .toSortedMap(String.CASE_INSENSITIVE_ORDER)

            tvResultadoEspontanea.text =
                formatarResultados(espontaneos)


            // ----------------------------
            // PESQUISA ESTIMULADA
            // ----------------------------

            val estimulados = pesquisas
                .mapNotNull { it.candidatoEstimulado }
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .groupingBy { it }
                .eachCount()
                .toSortedMap(String.CASE_INSENSITIVE_ORDER)

            tvResultadoEstimulada.text =
                formatarResultados(estimulados)


            // ----------------------------
            // PROBLEMAS
            // ----------------------------

            val problemas = pesquisas
                .mapNotNull { it.problemas }
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .groupingBy { it }
                .eachCount()
                .toSortedMap(String.CASE_INSENSITIVE_ORDER)

            tvResultadoProblemas.text =
                formatarResultados(problemas)
        }
    }

    private fun formatarResultados(
        resultados: Map<String, Int>
    ): String {

        if (resultados.isEmpty()) {
            return "Nenhum resultado disponível."
        }

        return resultados.entries.joinToString("\n") { resultado ->
            "${resultado.key}: ${resultado.value}"
        }
    }
}