package com.bonobostudios.amigosenmarcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ToolsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)

        var dinamicaBtn = findViewById<Button>(R.id.irADinamicas)
        var temasBtn = findViewById<Button>(R.id.irATemas)
        var back = findViewById<ImageView>(R.id.backEnTools)

        dinamicaBtn.setOnClickListener {
            var intent : Intent = Intent(this, DinamicsActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener{
            onBackPressed()
        }
    }
}
