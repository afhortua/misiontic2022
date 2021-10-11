package com.example.misiontic.webService

import com.example.misiontic.models.POI
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("poi")
    suspend fun requestPois(): ArrayList<POI>
}