package com.example.twittelumapp.viewmodel

import com.example.twittelumapp.TweetelumAplication
import com.example.twittelumapp.database.TweetellumDatabase

object Injetor {

    private val contexto = TweetelumAplication.getInstance()

    private val database = TweetellumDatabase.getInstance(contexto)

    fun tweetDao() = database.tweetDao()
}