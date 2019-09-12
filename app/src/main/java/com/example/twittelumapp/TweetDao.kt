package com.example.twittelumapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.twittelumapp.model.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salvar(tweet: Tweet)

    @Query("Select * from Tweet")
    fun listar(): List<Tweet>
}