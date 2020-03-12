package com.bonobostudios.amigosenmarcha

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class AdapterNinios(options: FirestoreRecyclerOptions<Miembro>, contar: (Int) -> Unit) :
    FirestoreRecyclerAdapter<Miembro, AdapterNinios.ViewHolder>(options) {
    //Instanciar las vistas que necesito
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var nombreKid: TextView = itemView.findViewById(R.id.nombre_del_ninio)
        var btnsi: Button = itemView.findViewById(R.id.si)
        var btnno: Button = itemView.findViewById(R.id.no)
        var btnperm: Button = itemView.findViewById(R.id.permiso)


        fun setOnClickListeners() {
            btnsi.setOnClickListener(this)
            btnno.setOnClickListener(this)
            btnperm.setOnClickListener(this)
        }

        fun contadores(num: Int): Int {
            if (num == 1) {
                return 1
            }
            if (num == 2) {
                return 0
            }
            return 0
        }

        override fun onClick(v: View?) {
            when (v?.getId()) {
                R.id.si -> {
                    btnsi.setBackgroundColor(0x00695C)
                    btnno.setBackgroundResource(R.drawable.button_list)
                    btnperm.setBackgroundResource(R.drawable.button_list)
                    Log.d("NO", "Tocaste YES2")
                    var cont = contadores(1)
                    Log.d("NUM", cont.toString())
                }
                R.id.no -> {
                    btnsi.setBackgroundResource(R.drawable.button_list)
                    btnno.setBackgroundColor(0x00695C)
                    btnperm.setBackgroundResource(R.drawable.button_list)
                    Log.d("NO", "Tocaste NO2")
                    var cont = contadores(2)
                    Log.d("NUM", cont.toString())
                }
                R.id.permiso -> {
                    btnsi.setBackgroundResource(R.drawable.button_list)
                    btnno.setBackgroundResource(R.drawable.button_list)
                    btnperm.setBackgroundColor(0x00695C)
                    Log.d("NO", "Tocaste PRME21")
                    var cont = contadores(1)
                    Log.d("NUM", cont.toString())
                }
            }
        }
    }


    //Crea la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_kids, parent, false)
        return ViewHolder(inflater)
    }


    //Valores de las vistas
    override fun onBindViewHolder(p0: ViewHolder, p1: Int, p2: Miembro) {
        p0.nombreKid.setText(p2.nombre)
        p0.setOnClickListeners()
    }
}