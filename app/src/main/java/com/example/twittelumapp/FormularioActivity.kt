package com.example.twittelumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class FormularioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        salvar.setOnClickListener {
            val mensagem = campoMensagem.text.toString()
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}