package com.example.twittelumapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.twittelumapp.model.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salvar(tweet: Tweet)

    @Query("Select * from Tweet")
    fun listar(): LiveData<List<Tweet>>

    @Delete
    fun deletar(tweet: Tweet)
}