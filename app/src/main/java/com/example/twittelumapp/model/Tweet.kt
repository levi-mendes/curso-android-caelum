package com.example.twittelumapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tweet (val conteudo: String,
                  @PrimaryKey(autoGenerate = true) val id: Int = 0)