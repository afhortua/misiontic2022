package com.example.misiontic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.databinding.FragmentPoiListBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class POIListFragment() : Fragment() {

    private lateinit var binding: FragmentPoiListBinding
    private lateinit var poiAdapter: POIListAdapter
    private lateinit var poiList: ArrayList<POI>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPoiListBinding.inflate(layoutInflater, container, false)
        initRecycler()
        createPOI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(this.context as AppCompatActivity){
            this.setTitle("Lista POI")
        }

    }

    private fun initRecycler() {
        poiList = arrayListOf()
        binding.rvList.layoutManager = LinearLayoutManager(this.context)
        poiAdapter = POIListAdapter(poiList)
        binding.rvList.adapter = poiAdapter
    }

    private fun loadData(inFile: String): String {
        var content = ""
        with(this.context as AppCompatActivity){
            try {
                val stream = assets.open(inFile)
                val size = stream.available()
                val buffer = ByteArray(size)
                stream.read(buffer)
                stream.close()
                content = String(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return content
    }

    private fun createPOI() {
        val data=loadData("poi_list.json")
        try {
            val poiJSON = JSONArray(data)
            for (i in 0 until poiJSON.length()) {
                val userJSON = poiJSON.getJSONObject(i)
                val user = POI(
                    userJSON.getString("image"),
                    userJSON.getString("name"),
                    userJSON.getString("description"),
                    userJSON.getString("location"),
                    userJSON.getString("detail"),
                    userJSON.getString("temperature"),
                    userJSON.getString("todo")
                )
                Log.d("poi",user.toString())
                poiList.add(user)
            }
            poiAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
