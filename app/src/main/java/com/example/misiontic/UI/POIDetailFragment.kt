package com.example.misiontic.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.models.POI
import com.example.misiontic.models.ToDoPOI
import com.example.misiontic.adapters.ToDoPOIAdapter
import com.example.misiontic.databinding.FragmentPoiDetailBinding
import com.example.misiontic.viewmodel.POIviewModel
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException

class POIDetailFragment() : Fragment() {

    private lateinit var binding: FragmentPoiDetailBinding
    private lateinit var todoAdapter: ToDoPOIAdapter
    private lateinit var todoList: ArrayList<ToDoPOI>
    private lateinit var titulo: String
    private lateinit var model: POIviewModel

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
        model = ViewModelProvider(requireActivity()).get(POIviewModel::class.java)
        initRecycler()
        observeLiveData()

    }

    private fun observeLiveData() {
        model.getSelected().observe(viewLifecycleOwner, { poi->
            titulo=poi.name
            Picasso.get().load(poi.image).into(binding.foto)
            binding.infoLorem.text=poi.detail
            binding.temperaturaLorem.text=poi.temperature
            binding.ubicacionLorem.text=poi.location
            //createTODO(poi.todo)
            requireActivity().setTitle(poi.name)
        })

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

