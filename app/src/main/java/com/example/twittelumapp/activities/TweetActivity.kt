package com.example.twittelumapp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import com.example.twittelumapp.R
import com.example.twittelumapp.model.Tweet
import com.example.twittelumapp.util.decodificaParaBase64
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var caminhoFoto: String? = null

    // de que maneira essa fabrica vai ser usada pra criar Viewmodels de outras "ViewMoldels"
    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_salvar     -> salvar()
            android.R.id.home    -> finish()
            R.id.menu_tirar_foto -> tirarFoto()
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TIRAR_FOTO && resultCode == Activity.RESULT_OK) {
            carregaFoto()
        }
    }

    private fun tirarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val foto = defineCaminhoFoto()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(intent, TIRAR_FOTO)
    }

    //todo: qual o significado do retorno Uri?
    private fun defineCaminhoFoto(): Uri? {
        caminhoFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(caminhoFoto)

        return FileProvider.getUriForFile(this, "MeuProvider", arquivo)
    }

    private fun salvar() {

        val tweet = criaTweet()

        viewModel.salvar(tweet)
        Toast.makeText(this, "$tweet", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun criaTweet(): Tweet {
        val mensagem = et_campo_mensagem.text.toString()
        val foto: String? = iv_tweet_foto.tag as String?

        return Tweet(mensagem, foto)
    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(caminhoFoto)
        val bitmapScaled = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
        iv_tweet_foto.setImageBitmap(bitmapScaled)
        val fotoNaBase64 = bitmapScaled.decodificaParaBase64()
        iv_tweet_foto.tag = fotoNaBase64
        iv_tweet_foto.scaleType = ImageView.ScaleType.FIT_XY
    }

    companion object {
        private const val TIRAR_FOTO = 123
    }
}