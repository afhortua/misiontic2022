package com.example.misiontic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.misiontic.models.POI

class POIviewModel : ViewModel() {

    private val selected = MutableLiveData<POI>()

    fun getSelected(): LiveData<POI> = selected

    fun select(poi: POI) {
        selected.value = poi
    }
}