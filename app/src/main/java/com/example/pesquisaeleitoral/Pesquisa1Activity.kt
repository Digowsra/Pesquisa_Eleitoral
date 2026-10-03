package com.example.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pesquisaeleitoral.database.DatabaseProvider
import com.example.pesquisaeleitoral.database.Pesquisa
import kotlinx.coroutines.launch

class Pesquisa1Activity : AppCompatActivity() {

    private lateinit var etCandidato: EditText
    private lateinit var btPesquisaUm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_pesquisa1)

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

        etCandidato = findViewById(R.id.etCandidato)
        btPesquisaUm = findViewById(R.id.btPesquisaUm)

        btPesquisaUm.setOnClickListener {

            val candidato = etCandidato.text.toString().trim()

            if (candidato.isEmpty()) {
                etCandidato.error = "Digite o nome do candidato"
                return@setOnClickListener
            }

            lifecycleScope.launch {

                val pesquisa = Pesquisa(
                    candidatoEspontaneo = candidato
                )

                val idPesquisa = DatabaseProvider
                    .getDatabase(this@Pesquisa1Activity)
                    .pesquisaDao()
                    .inserir(pesquisa)

                val intent = Intent(
                    this@Pesquisa1Activity,
                    Pesquisa2Activity::class.java
                )

                intent.putExtra("ID_PESQUISA", idPesquisa)

                startActivity(intent)
            }
        }
    }
}