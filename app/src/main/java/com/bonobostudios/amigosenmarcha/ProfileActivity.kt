package com.bonobostudios.amigosenmarcha

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        fun cargarPreferencias(): String {
            var preferences: SharedPreferences =
                getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val usuario = preferences.getString("user", "no hay nada allí")

            return usuario
        }

        var name = findViewById<TextView>(R.id.userName)
        var back = findViewById<ImageView>(R.id.regresar)

        name.setText(cargarPreferencias())
        back.setOnClickListener{
            onBackPressed()
        }
    }
}
