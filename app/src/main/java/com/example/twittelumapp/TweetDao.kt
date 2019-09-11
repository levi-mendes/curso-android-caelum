package com.example.twittelumapp

import androidx.room.Dao
import androidx.room.Insert
import com.example.twittelumapp.model.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salvar(tweet: Tweet)
}