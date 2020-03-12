package com.bonobostudios.amigosenmarcha

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()

        var usuario: EditText = findViewById(R.id.editusername)
        var contra: EditText = findViewById(R.id.editpassword)
        val iniciarSesion: Button = findViewById(R.id.buttonis)


        fun guardarPreferencias(UserCampo : String){
            var preferences : SharedPreferences = getSharedPreferences("usuario",Context.MODE_PRIVATE)

            var editor : SharedPreferences.Editor = preferences.edit()
            editor.putString("user",UserCampo)
            editor.commit()
        }

        fun ocultarTeclado() {
            var view: View = this.getCurrentFocus()
            if (view != null) {
                var imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }

        fun getDatos(idUser: String, pass: String) {

            Log.d("TAG", idUser + "loc")
            Log.d("TAG", pass + "loc")

            var datos = db.collection("usuarios").document(idUser)

            datos.get().addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot != null) {

                    var userEscrito = findViewById<EditText>(R.id.editusername)
                    var contraEscrita = findViewById<EditText>(R.id.editpassword)

                    val UserName = documentSnapshot.getString("username")
                    val PassWord = documentSnapshot.getString("password")
                    val AccountType = documentSnapshot.getBoolean("account")

                    Log.d("TAG", UserName + "obtained")
                    Log.d("TAG", PassWord + "obtained")
                    Log.d("TAG", AccountType.toString() + "obtained")

                    if (idUser == UserName) {
                        if (pass == PassWord) {
                            if (AccountType == true) {
                                datos.update("online",true)

                                var intent = Intent(this, NormalLauncherActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                                finish()
                            }
                            if (AccountType == false) {
                                datos.update("online",true)
                                var intent = Intent(this, CoordLauncherActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                                finish()
                            }

                        } else {
                            contraEscrita.setText("")
                            Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT)
                                .show()
                        }

                    } else {
                        userEscrito.setText("")
                        Toast.makeText(this, "Usuario incorrecto.", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(
                        this,
                        "Nombre de usuario o contraseña incorrectos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun iniciar() {
            var userEscrito = usuario.getText().toString()
            var contraEscrita = contra.getText().toString()

            Log.d("TAG", userEscrito)
            Log.d("TAG", contraEscrita)

            if (userEscrito.length == 0 && contraEscrita.length == 0) {

                Toast.makeText(this, "Rellena todos los campos, por favor.", Toast.LENGTH_SHORT)
                    .show()
            } else if (userEscrito.length == 0) {
                usuario.setText(" ")
                Toast.makeText(this, "Escribe un nombre de usuario.", Toast.LENGTH_SHORT).show()
            } else if (contraEscrita.length == 0) {
                contra.setText(" ")
                Toast.makeText(this, "Escribe una contraseña.", Toast.LENGTH_SHORT).show()
            } else {
                guardarPreferencias(userEscrito)
                getDatos(userEscrito, contraEscrita)
            }
        }

        //iniciando

        iniciarSesion.setOnClickListener {
            ocultarTeclado()
            iniciar()
        }


//setear fuentes
        val fuente: TextView = findViewById(R.id.AEMletras)
        val nameFont = "fuentes/GreatWishes.otf"

        var script: Typeface = Typeface.createFromAsset(getAssets(), nameFont)
        fuente.setTypeface(script)

    }
}