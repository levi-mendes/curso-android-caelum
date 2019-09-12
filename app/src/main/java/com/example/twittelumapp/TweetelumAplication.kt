package com.example.twittelumapp

import android.app.Application

class TweetelumAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {

        private lateinit var instance: TweetelumAplication

        fun getInstance() = instance
    }
}