package com.example.twittelumapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.twittelumapp.R
import com.example.twittelumapp.database.TweetellumDatabase
import com.example.twittelumapp.model.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class FormularioActivity : AppCompatActivity() {

    // de que maneira essa fabrica vai ser usada pra criar Viewmodels de outras "ViewMoldels"
    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_salvar -> salvar()
            android.R.id.home -> finish()
        }

        return true
    }

    private fun salvar() {
        val mensagem = et_campo_mensagem.text.toString()
        val tweet = Tweet(mensagem)

        viewModel.salvar(tweet)

        Toast.makeText(this, "$tweet", Toast.LENGTH_SHORT).show()
        finish()
    }
}