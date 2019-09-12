package com.example.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twittelumapp.database.TweetRepository
import com.example.twittelumapp.model.Tweet

class TweetViewModel(private val repository: TweetRepository): ViewModel()  {

    fun listar() = repository.listar()

    fun salvar(tweet: Tweet) = repository.salvar(tweet)
}