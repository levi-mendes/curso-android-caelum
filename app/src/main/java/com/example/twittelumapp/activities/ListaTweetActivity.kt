package com.example.twittelumapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.twittelumapp.R
import com.example.twittelumapp.adapter.ListaTweetsAdapter
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
            val intent = Intent(this, TweetActivity::class.java)
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
        return Observer {
            lista.adapter = ListaTweetsAdapter(it)
        }
    }
}