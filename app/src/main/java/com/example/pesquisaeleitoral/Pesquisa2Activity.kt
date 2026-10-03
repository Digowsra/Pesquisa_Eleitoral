package com.example.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pesquisaeleitoral.database.DatabaseProvider
import kotlinx.coroutines.launch

class Pesquisa2Activity : AppCompatActivity() {

    private lateinit var rgCandidatos: RadioGroup
    private lateinit var btPesquisaDois: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_pesquisa2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        rgCandidatos = findViewById(R.id.rgCandidatos)
        btPesquisaDois = findViewById(R.id.btPesquisaDois)

        // Recebe o ID criado na Pesquisa1Activity
        val idPesquisa = intent.getLongExtra("ID_PESQUISA", -1)

        btPesquisaDois.setOnClickListener {

            val candidatoSelecionado = when (rgCandidatos.checkedRadioButtonId) {

                R.id.rbCM -> "Capitã Marvel"

                R.id.rbHA -> "Homem-Aranha"

                R.id.rbHF -> "Homem de Ferro"

                R.id.rbt -> "Thor"

                R.id.rbVN -> "Viúva Negra"

                else -> null
            }

            if (candidatoSelecionado == null) {

                Toast.makeText(
                    this,
                    "Selecione um candidato",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (idPesquisa == -1L) {

                Toast.makeText(
                    this,
                    "Erro ao identificar a pesquisa",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            lifecycleScope.launch {

                DatabaseProvider
                    .getDatabase(this@Pesquisa2Activity)
                    .pesquisaDao()
                    .salvarCandidatoEstimulado(
                        idPesquisa,
                        candidatoSelecionado
                    )
                val intent = Intent(
                    this@Pesquisa2Activity,
                    Pesquisa3Activity::class.java
                )

                intent.putExtra("ID_PESQUISA", idPesquisa)
                startActivity(intent)
            }
        }
    }
}