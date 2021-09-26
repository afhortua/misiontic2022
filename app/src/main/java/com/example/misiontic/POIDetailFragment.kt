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

class POIDetailFragment(var detailPOI: POI) : Fragment() {

    private lateinit var binding: FragmentPoiDetailBinding
    private lateinit var todoAdapter: ToDoPOIAdapter
    private lateinit var todoList: ArrayList<ToDoPOI>
    private lateinit var titulo: String

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
        createDetailPOI()
        with(this.context as AppCompatActivity) {
            this.setTitle(titulo)
        }
    }

    private fun createDetailPOI() {
        titulo = detailPOI.name
        binding.temperaturaLorem.text = detailPOI.temperature
        binding.infoLorem.text = detailPOI.detail
        binding.ubicacionLorem.text = detailPOI.location
        Picasso.get().load(detailPOI.image).into(binding.foto)
        createTODO(detailPOI.todo)
    }

    private fun initRecycler() {
        todoList = arrayListOf()
        val layout = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPoiToDo.layoutManager = layout
        todoAdapter = ToDoPOIAdapter(todoList)
        binding.rvPoiToDo.adapter = todoAdapter
    }

    private fun createTODO(data: String?) {
        try {
            val poiJSON = JSONArray(data)
            for (i in 0 until poiJSON.length()) {
                val todoJSON = poiJSON.getJSONObject(i)
                val todo = ToDoPOI(
                    todoJSON.getString("name"),
                    todoJSON.getString("todo"),
                    todoJSON.getString("image")
                )
                todoList.add(todo)
            }
            todoAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}

