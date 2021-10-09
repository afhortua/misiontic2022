package com.example.misiontic.models

data class POI(
    val image: String,
    val name: String,
    val description: String,
    val location: String,
    val detail:String,
    val temperature:String,
    val latitude: Double,
    val longitude: Double
    //val todo: String
)
