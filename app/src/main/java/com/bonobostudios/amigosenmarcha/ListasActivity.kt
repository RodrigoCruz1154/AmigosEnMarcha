package com.bonobostudios.amigosenmarcha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ListasActivity : AppCompatActivity() {

    lateinit var recyclerViewNinios: RecyclerView
    lateinit var recyclerViewGuias: RecyclerView
    lateinit var nAdapteer: AdapterNinios
    lateinit var gAdapteer: AdapterGuias
    lateinit var cantninios: TextView
    lateinit var cantguias: TextView
    lateinit var backlist: ImageView

    var cantKids: Int = 0
    var cantGuides: Int = 0

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listas)

        //Lista de los niños de la tienda
        recyclerViewNinios = findViewById(R.id.recycler_view_niños)
        recyclerViewNinios.layoutManager

        var query: Query = db.collection("tienda1")

        var firebaseRecyclerOptions: FirestoreRecyclerOptions<Miembro> =
            FirestoreRecyclerOptions.Builder<Miembro>().setQuery(query, Miembro::class.java).build()

        nAdapteer = AdapterNinios(firebaseRecyclerOptions,{num->Log.d("algo",num.toString())})
        nAdapteer.notifyDataSetChanged()

        recyclerViewNinios.setAdapter(nAdapteer)

        query.get().addOnSuccessListener { documents ->
            for (document in documents) {
                cantKids++

            }
            cantninios = findViewById(R.id.cantNiniosenTotal)
            cantninios.setText(cantKids.toString())
        }


        //Lista de los guias de la tienda
        recyclerViewGuias = findViewById(R.id.recycler_view_guias)
        recyclerViewGuias.layoutManager

        var queryG: Query = db.collection("guias")
        var firebaseRecyclerOptionsG: FirestoreRecyclerOptions<Miembro> =
            FirestoreRecyclerOptions.Builder<Miembro>().setQuery(queryG, Miembro::class.java)
                .build()

        gAdapteer = AdapterGuias(firebaseRecyclerOptionsG)
        gAdapteer.notifyDataSetChanged()

        recyclerViewGuias.setAdapter(gAdapteer)

        queryG.get().addOnSuccessListener { documents ->
            for (document in documents) {
                cantGuides++
            }
            cantguias = findViewById(R.id.cantGuiasenTotal)
            cantguias.setText(cantGuides.toString())
        }

        backlist = findViewById(R.id.backList)
        backlist.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onStart() {
        super.onStart()
        nAdapteer.startListening()
        gAdapteer.startListening()
    }

    override fun onStop() {
        super.onStop()
        nAdapteer.stopListening()
        gAdapteer.stopListening()
    }
}
