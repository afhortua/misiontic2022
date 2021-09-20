package com.example.misiontic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.databinding.ActivityPoiListBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class POI_list : AppCompatActivity() {

    private lateinit var binding: ActivityPoiListBinding
    private lateinit var poiAdapter: PoiAdapter
    private lateinit var poiList: ArrayList<POI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPoiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        createPOI()
    }

    private fun initRecycler(){
        poiList = arrayListOf()
        binding.rvPOI.layoutManager = LinearLayoutManager(this)
        poiAdapter=PoiAdapter(poiList)
        binding.rvPOI.adapter = poiAdapter
    }

    private fun loadData(inFile: String): String {
        var content = ""
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
        return content
    }

    private fun createPOI() {
        val data = loadData("poi_list.json")
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