package com.example.twittelumapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.twittelumapp.database.dao.TweetDao
import com.example.twittelumapp.model.Tweet

@Database(entities = [Tweet::class], version = 2)
abstract class TweetellumDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

   companion object {

       private var database: TweetellumDatabase? = null
       private const val DATABASE_NAME = "Tweetelum-db"

       fun getInstance(context: Context): TweetellumDatabase {
           return database
               ?: criarDatabase(
                   context
               ).also { database = it }
       }

       private fun criarDatabase(context: Context): TweetellumDatabase {
           return Room.databaseBuilder(context, TweetellumDatabase::class.java,
               DATABASE_NAME
           )
               .allowMainThreadQueries()
               .addMigrations(Migration1Para2)
               .build()
       }
   }

    object Migration1Para2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("alter table Tweet add column foto text")
        }
    }
}