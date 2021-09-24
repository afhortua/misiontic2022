package com.example.misiontic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.databinding.FragmentPoiListBinding
import org.json.JSONArray
import org.json.JSONException

class POIListFragment(var data:String) : Fragment() {

    private lateinit var binding: FragmentPoiListBinding
    private lateinit var poiAdapter: PoiAdapter
    private lateinit var poiList: ArrayList<POI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tag", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("tag", "onCreateView")
        binding = FragmentPoiListBinding.inflate(layoutInflater, container, false)
        initRecycler()
        createPOI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tag", "onViewCreated")

    }

    private fun initRecycler() {
        poiList = arrayListOf()
        binding.rvList.layoutManager = LinearLayoutManager(this.context)
        poiAdapter = PoiAdapter(poiList)
        binding.rvList.adapter = poiAdapter


    }

    private fun createPOI() {
        try {
            val poisJSON = JSONArray(data)
            for (i in 0 until poisJSON.length()) {
                val userJSON = poisJSON.getJSONObject(i)
                val user = POI(
                    userJSON.getString("image"),
                    userJSON.getString("name"),
                    userJSON.getString("description"),
                    userJSON.getString("location")
                )
                poiList.add(user)
            }
            poiAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
