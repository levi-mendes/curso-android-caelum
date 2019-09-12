package com.example.twittelumapp.database

import com.example.twittelumapp.database.dao.TweetDao
import com.example.twittelumapp.model.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {

    fun listar() = fonteDeDados.listar()

    fun salvar(tweet: Tweet) = fonteDeDados.salvar(tweet)

    fun deletar(tweet: Tweet) = fonteDeDados.deletar(tweet)
}