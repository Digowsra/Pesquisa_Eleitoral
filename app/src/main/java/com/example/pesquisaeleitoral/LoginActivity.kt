package com.example.pesquisaeleitoral

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var btEntrar    : Button
    private lateinit var btFecharApp : Button
    private lateinit var etLogin     : TextView
    private lateinit var etSenha     : TextView
    private lateinit var tvError     : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        btEntrar = findViewById(R.id.btEntrar)
        btFecharApp = findViewById(R.id.btFecharApp)
        etLogin = findViewById(R.id.etLogin)
        etSenha = findViewById(R.id.etSenha)
        tvError = findViewById(R.id.tvError)

        btEntrar.setOnClickListener {

            if (etLogin.text.toString() == "JeanAlberth" && etSenha.text.toString() == "09150212"){
                var pesquisa : Intent
                pesquisa = Intent(this, Pesquisa1Activity::class.java)
                startActivity(pesquisa)
            }else if(etLogin.text.toString() == "DiegoAlbuquerque" && etSenha.text.toString() == "Di3go0212") {
                var pesquisa : Intent
                pesquisa = Intent(this, Pesquisa1Activity::class.java)
                startActivity(pesquisa)
            }else{
                tvError.text = "Senha ou Login Errados"
            }


        }

        btFecharApp.setOnClickListener {
            finishAndRemoveTask()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}