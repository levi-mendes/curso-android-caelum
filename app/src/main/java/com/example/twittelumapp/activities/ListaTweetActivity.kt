package com.example.twittelumapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.twittelumapp.R
import com.example.twittelumapp.database.TweetellumDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lista.*

class ListaTweetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val database = TweetellumDatabase.getInstance(this)
        val tweetDao = database.tweetDao()
        val listaTweets = tweetDao.listar()
        lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTweets)

        lista.setOnItemClickListener { _, _, position, _ ->
            val item = lista.adapter.getItem(position)
            Snackbar.make(lista, item.toString(), Snackbar.LENGTH_LONG).show()
        }

        fab.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }
}