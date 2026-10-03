package com.example.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pesquisaeleitoral.database.DatabaseProvider
import kotlinx.coroutines.launch

class Pesquisa3Activity : AppCompatActivity() {

    private lateinit var checkBoxes: List<CheckBox>
    private lateinit var btProximo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_pesquisa3)

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

        // Recupera o ID da pesquisa criado na Pesquisa1Activity
        val idPesquisa = intent.getLongExtra("ID_PESQUISA", -1)

        // CheckBoxes da tela
        checkBoxes = listOf(
            findViewById(R.id.cbAF),
            findViewById(R.id.cbE),
            findViewById(R.id.cbEst),
            findViewById(R.id.ckL),
            findViewById(R.id.cbP),
            findViewById(R.id.cbSB),
            findViewById(R.id.cbSaude),
            findViewById(R.id.cbSeguranca),
            findViewById(R.id.cbT)
        )

        btProximo = findViewById(R.id.bt1tela5)

        // Impede que sejam selecionadas mais de 3 opções
        checkBoxes.forEach { checkBox ->

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked) {

                    val quantidadeSelecionada =
                        checkBoxes.count { it.isChecked }

                    if (quantidadeSelecionada > 3) {

                        buttonView.isChecked = false

                        Toast.makeText(
                            this,
                            "Selecione no máximo 3 opções",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        btProximo.setOnClickListener {

            val selecionados = checkBoxes.filter { it.isChecked }

            // Obrigatoriamente precisa selecionar 3
            if (selecionados.size != 3) {

                Toast.makeText(
                    this,
                    "Selecione exatamente 3 opções",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Verifica se recebemos um ID válido
            if (idPesquisa == -1L) {

                Toast.makeText(
                    this,
                    "Erro ao identificar a pesquisa",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Pega o texto das três opções
            val problemas = selecionados
                .map { it.text.toString().trim() }
                .joinToString("|")

            lifecycleScope.launch {

                DatabaseProvider
                    .getDatabase(this@Pesquisa3Activity)
                    .pesquisaDao()
                    .salvarProblemas(
                        idPesquisa,
                        problemas
                    )

                // Próxima etapa
                val intent = Intent(
                    this@Pesquisa3Activity,
                    DadosEntrevistadoActivity::class.java
                )

                intent.putExtra(
                    "ID_PESQUISA",
                    idPesquisa
                )

                startActivity(intent)
            }
        }
    }
}