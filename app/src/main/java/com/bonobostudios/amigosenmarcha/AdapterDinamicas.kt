package com.bonobostudios.amigosenmarcha
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

public class AdapterDinamicas(options: FirestoreRecyclerOptions<Dinamicas>) :
    FirestoreRecyclerAdapter<Dinamicas, AdapterDinamicas.ViewHolder>(options) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombreDim = itemView.findViewById<TextView>(R.id.dinamica_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dinamics_view, parent, false)
        return ViewHolder(inflater)
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int, p2: Dinamicas) {
        p0.nombreDim.setText(p2.nombre)
    }


}