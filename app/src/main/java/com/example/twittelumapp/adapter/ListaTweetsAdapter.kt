package com.example.twittelumapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.twittelumapp.R
import com.example.twittelumapp.model.Tweet
import com.example.twittelumapp.util.Carregador
import kotlinx.android.synthetic.main.item_tweet.view.*

class ListaTweetsAdapter(private val tweets: List<Tweet>) : BaseAdapter() {


    override fun getCount(): Int = tweets.size

    override fun getItem(position: Int): Any = tweets[position]

    override fun getItemId(position: Int): Long = tweets[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = convertView ?: inflater.inflate(R.layout.item_tweet, parent, false)

        view.item_conteudo.text = tweet.conteudo
        view.item_foto.visibility = View.GONE

        tweet.foto?.let {
            view.item_foto.visibility = View.VISIBLE
            view.item_foto.setImageBitmap(Carregador.decodifica(it))
        }

        return view
    }
}