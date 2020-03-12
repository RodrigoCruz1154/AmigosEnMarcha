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

public class AdapterGuias(options: FirestoreRecyclerOptions<Miembro>) :
    FirestoreRecyclerAdapter<Miembro, AdapterGuias.ViewHolder>(options) {


    //Instanciar las vistas que necesito
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombreGuid: TextView = itemView.findViewById<TextView>(R.id.nombre_del_guia)
        var clicks : ToggleButton = itemView.findViewById(R.id.toogle_button_p)

    }


    //Crea la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_guide, parent, false)
        return ViewHolder(inflater)
    }


    //Valores de las vistas
    override fun onBindViewHolder(p0: ViewHolder, p1: Int, p2: Miembro) {
        p0.nombreGuid.setText(p2.nombre)
        p0.clicks.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Log.d("SI","IT WORKS!")
            }else{
                Log.d("NO","IT WORKS!")
            }

        }
    }


}