package com.example.pesquisaeleitoral

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pesquisaeleitoral.database.DadosEntrevistado
import com.example.pesquisaeleitoral.database.DatabaseProvider
import kotlinx.coroutines.launch

class DadosEntrevistadoActivity : AppCompatActivity() {

    private lateinit var etNomeCandidato: EditText
    private lateinit var etCelular: EditText
    private lateinit var btFinalizar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_dados_entrevistado)

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

        etNomeCandidato = findViewById(R.id.etNomeCandidato)
        etCelular = findViewById(R.id.etCelular)
        btFinalizar = findViewById(R.id.btFinalizar)

        btFinalizar.setOnClickListener {

            val nome = etNomeCandidato.text.toString().trim()
            val celular = etCelular.text.toString().trim()

            // Se nenhum dado foi informado, apenas finaliza.
            if (nome.isEmpty() && celular.isEmpty()) {
                finalizarPesquisa()
                return@setOnClickListener
            }

            // Se algum dado foi fornecido, salva separadamente.
            lifecycleScope.launch {

                val dadosEntrevistado = DadosEntrevistado(
                    nome = nome,
                    celular = celular
                )

                DatabaseProvider
                    .getDatabase(this@DadosEntrevistadoActivity)
                    .dadosEntrevistadoDao()
                    .inserir(dadosEntrevistado)

                finalizarPesquisa()
            }
        }
    }

    private fun finalizarPesquisa() {

        Toast.makeText(
            this,
            "Pesquisa finalizada com sucesso!",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}