package com.example.misiontic.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.misiontic.R
import com.example.misiontic.viewmodel.POIviewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng

class MapsFragment : Fragment(){

    private lateinit var coord: LatLng
    private lateinit var title: String
    private lateinit var model: POIviewModel

    private val callback = OnMapReadyCallback{ googleMap ->
        model.poisLiveData.observe(viewLifecycleOwner,{
            for(POI in it){
                googleMap.addMarker(MarkerOptions().position(LatLng(POI.latitude,POI.longitude)).title(POI.name))
            }
        })
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coord,13f),
            2000,
            null
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(POIviewModel::class.java)
        observeLiveData()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun observeLiveData() {
        model.getSelected().observe(viewLifecycleOwner, {
            title=it.name
            coord= LatLng(it.latitude,it.longitude)
        })
    }
}