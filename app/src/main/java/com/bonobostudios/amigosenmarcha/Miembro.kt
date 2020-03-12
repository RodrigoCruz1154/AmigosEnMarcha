package com.bonobostudios.amigosenmarcha

class Miembro{
    lateinit var nombre : String
    var tienda : Int = 0
    var asistencia : Int = 0

    fun Miembro(){

    }

    fun miembro(nombre: String, tienda:Int,asistencia:Int){
        this.nombre = nombre
        this.tienda = tienda
        this.asistencia = asistencia
    }


}