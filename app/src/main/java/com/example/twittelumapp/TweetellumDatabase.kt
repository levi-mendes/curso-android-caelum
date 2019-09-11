package com.example.twittelumapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twittelumapp.model.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TweetellumDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

   companion object {

       private var database: TweetellumDatabase? = null
       private const val DATABASE_NAME = "Tweetelum-db"

       fun getInstance(context: Context): TweetellumDatabase {
           return database ?: criarDatabase(context).also { database = it }
       }

       private fun criarDatabase(context: Context): TweetellumDatabase {
           return Room.databaseBuilder(context, TweetellumDatabase::class.java, DATABASE_NAME)
               .allowMainThreadQueries()
               .build()
       }
   }
}