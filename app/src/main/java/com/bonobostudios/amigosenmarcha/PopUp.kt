package com.bonobostudios.amigosenmarcha

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

class PopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)

        var dm  = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(dm)

        var width : Int = dm.widthPixels
        var height : Int = dm.heightPixels

        getWindow().setLayout((width*.8).toInt(),(height*.3).toInt())
    }
}
