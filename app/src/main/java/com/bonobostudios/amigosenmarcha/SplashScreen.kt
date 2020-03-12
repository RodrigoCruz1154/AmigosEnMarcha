package com.bonobostudios.amigosenmarcha

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.os.postDelayed
import com.google.firebase.firestore.FirebaseFirestore
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Thread.sleep

class SplashScreen : AppCompatActivity() {

    val SPLASH_TIME_OUT = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        fun splash() {
            val image: ImageView = findViewById(R.id.imageView)
            var animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
            image.startAnimation(animation)
            Handler().postDelayed(Runnable() {
                fun run() {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                run()
            }, SPLASH_TIME_OUT.toLong())
        }

        fun cargarPreferencias(): String {
            var preferences: SharedPreferences =
                getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val usuario = preferences.getString("user", "no hay nada allí")

            if(usuario == ""){
                splash()
            }

            return usuario
        }

        FirebaseFirestore.getInstance().collection("usuarios").document(cargarPreferencias()).get()
            .addOnSuccessListener { document ->

                val encendido = document.getBoolean("online")
                val AccountType = document.getBoolean("account")

                if(encendido==true) {
                    if (AccountType == true) {
                        var intent = Intent(this, NormalLauncherActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if (AccountType == false) {
                        var intent = Intent(this, CoordLauncherActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                else{
                    splash()
                }
            }
    }
}
