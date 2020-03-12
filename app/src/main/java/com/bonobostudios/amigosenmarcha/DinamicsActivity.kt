package com.bonobostudios.amigosenmarcha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class DinamicsActivity : AppCompatActivity() {

    lateinit var recyclerViewDinamicas: RecyclerView
    lateinit var dAdapteer: AdapterDinamicas
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinamics)

        var back = findViewById<ImageView>(R.id.backenDinamics1)

        recyclerViewDinamicas = findViewById(R.id.recycler_view_dinamicas)
        recyclerViewDinamicas.layoutManager

        var query: Query = db.collection("dinamicas")

        var firebaseRecyclerOptions: FirestoreRecyclerOptions<Dinamicas> =
            FirestoreRecyclerOptions.Builder<Dinamicas>().setQuery(query, Dinamicas::class.java).build()

        dAdapteer = AdapterDinamicas(firebaseRecyclerOptions)
        dAdapteer.notifyDataSetChanged()

        recyclerViewDinamicas.setAdapter(dAdapteer)

        back.setOnClickListener{
            onBackPressed()
        }

    }

    override fun onStart() {
        super.onStart()
        dAdapteer.startListening()
    }

    override fun onStop() {
        super.onStop()
        dAdapteer.stopListening()
    }
}
