package com.bonobostudios.amigosenmarcha

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore

class CoordLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coord_launcher)

        fun cargarPreferencias(): String {
            var preferences: SharedPreferences =
                getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val usuario: String = preferences.getString("user", "no hay nada allí")

            return usuario
        }

        var boton = findViewById<Button>(R.id.button)

        boton.setOnClickListener {
            FirebaseFirestore.getInstance().collection("usuarios").document(cargarPreferencias())
                .update("online", false)
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
