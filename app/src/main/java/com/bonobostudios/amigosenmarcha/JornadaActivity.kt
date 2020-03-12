package com.bonobostudios.amigosenmarcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class JornadaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jornada)

        var back = findViewById<ImageView>(R.id.back)
        var pl = findViewById<Button>(R.id.irAPasarLista)

        back.setOnClickListener{
            onBackPressed()
        }
        pl.setOnClickListener{
            var intent = Intent(this, ListasActivity::class.java)
            startActivity(intent)
        }
    }
}
