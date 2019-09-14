package com.example.twittelumapp.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object Carregador {

    fun decodifica(foto: String): Bitmap {
        val decode: ByteArray = Base64.decode(foto, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(decode, 0, decode.size)
    }
}