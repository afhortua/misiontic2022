package com.example.misiontic.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.misiontic.models.POI
import com.example.misiontic.webService.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class POIviewModel : ViewModel() {

    private val selected = MutableLiveData<POI>()
    private var apiService = RetrofitFactory.apiService()
    private var pois = MutableLiveData<ArrayList<POI>>()
    var poisLiveData: LiveData<ArrayList<POI>> = pois

    //se obtiene el POI seleccionado del RecyclerView

    fun getSelected(): LiveData<POI> = selected

    //se guarda el POI seleccionado del RecyclerView

    fun select(poi: POI) {
        selected.value = poi
    }

    //se obtienen todos los POI del servicio web

    fun getPois(){
        viewModelScope.launch {
            pois.value = requestPOIS()
        }
    }

    //se hace la peticion al servicio de todos los POI y retorna un ArrayList

    private suspend fun requestPOIS(): ArrayList<POI>{
        return withContext(Dispatchers.IO){
            apiService.requestPois()
        }
    }
}