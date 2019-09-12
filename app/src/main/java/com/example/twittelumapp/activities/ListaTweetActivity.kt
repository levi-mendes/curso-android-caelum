package com.example.twittelumapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.twittelumapp.R
import com.example.twittelumapp.model.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista.*

class ListaTweetActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        viewModel.listar().observe(this, observer())

        lista.setOnItemClickListener { _, _, position, _ ->
            val item = lista.adapter.getItem(position) as Tweet
           deleteTweetConfirmation(item)
        }

        fab.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteTweetConfirmation(tweet: Tweet) {
        AlertDialog.Builder(this)
            .setMessage("Confirma a deleção do Tweet?")
            .setTitle("Atenção")
            .setIcon(R.drawable.ic_warning_black_24dp)
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deletar(tweet)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun observer() : Observer<List<Tweet>> {
        return Observer { lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it) }
    }

    /*
    override fun onResume() {
        super.onResume()

        val database = TweetellumDatabase.getInstance(this)
        val tweetDao = database.tweetDao()
        val listaTweets = tweetDao.listar()
        lista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTweets)
    }
     */
}