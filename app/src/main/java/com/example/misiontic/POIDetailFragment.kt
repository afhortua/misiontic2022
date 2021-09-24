package com.example.misiontic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.databinding.FragmentPoiDetailBinding
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class POIDetailFragment(var POIid: Int) : Fragment() {

    private lateinit var binding: FragmentPoiDetailBinding
    private lateinit var todoAdapter: ToDoPOIAdapter
    private lateinit var todoList: ArrayList<ToDoPOI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPoiDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        createDetailPOI(POIid)
    }

    private fun loadData(inFile: String): String {
        var content = ""
        val cont = this.context as AppCompatActivity
        with(cont) {
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

    private fun createDetailPOI(POIid: Int) {
        val data = loadData("poi_detail.json")
        try {
            val poiDetailJSON = JSONArray(data).getJSONObject(POIid)
            binding.titulo.text=poiDetailJSON.getString("name")
            binding.temperaturaLorem.text=poiDetailJSON.getString("temperature")
            binding.infoLorem.text=poiDetailJSON.getString("description")
            binding.ubicacionLorem.text=poiDetailJSON.getString("location")
            Picasso.get().load(poiDetailJSON.getString("image")).into(binding.foto)
            val poiToDo = poiDetailJSON.getJSONArray("todo")
            createTODO(poiToDo.toString())
            Log.d("TODO",poiToDo.length().toString())

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun initRecycler() {
        todoList = arrayListOf()
        val layout = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvPoiToDo.layoutManager=layout
        todoAdapter = ToDoPOIAdapter(todoList)
        binding.rvPoiToDo.adapter = todoAdapter
    }

    private fun createTODO(data:String) {
        try {
            val poiJSON = JSONArray(data)
            for (i in 0 until poiJSON.length()) {
                val todoJSON = poiJSON.getJSONObject(i)
                val todo = ToDoPOI(
                    todoJSON.getString("name"),
                    todoJSON.getString("todo")
                    )
                todoList.add(todo)
            }
            todoAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}

