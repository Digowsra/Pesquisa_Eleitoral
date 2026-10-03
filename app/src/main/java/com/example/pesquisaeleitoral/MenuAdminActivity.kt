package com.example.pesquisaeleitoral

import android.content.Intent
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

class MenuAdminActivity : AppCompatActivity() {

    private lateinit var btEleitor: Button
    private lateinit var btResultado: Button
    private lateinit var btSair: Button
    private lateinit var tvQuantidadeEntrevistados: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_admin)

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

        btEleitor = findViewById(R.id.btEleitor)
        btResultado = findViewById(R.id.btResultado)
        btSair = findViewById(R.id.btSair)
        tvQuantidadeEntrevistados = findViewById(R.id.textView16)

        btEleitor.setOnClickListener {
            val intent = Intent(
                this,
                EntrevistadosActivity::class.java
            )
            startActivity(intent)
        }

        btResultado.setOnClickListener {
            val intent = Intent(
                this,
                ResultadosActivity::class.java
            )
            startActivity(intent)
        }

        btSair.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        carregarQuantidadePesquisas()
    }

    private fun carregarQuantidadePesquisas() {

        lifecycleScope.launch {

            val quantidade = DatabaseProvider
                .getDatabase(this@MenuAdminActivity)
                .pesquisaDao()
                .contarPesquisas()

            tvQuantidadeEntrevistados.text =
                "Quantidade de entrevistados: $quantidade"
        }
    }
}