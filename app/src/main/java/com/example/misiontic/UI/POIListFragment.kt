package com.example.misiontic.UI

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.misiontic.models.POI
import com.example.misiontic.adapters.POIListAdapter
import com.example.misiontic.databinding.FragmentPoiListBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import com.example.misiontic.viewmodel.POIviewModel
import com.example.misiontic.R

class POIListFragment() : Fragment() {

    private lateinit var binding: FragmentPoiListBinding
    private lateinit var poiAdapter: POIListAdapter
    private lateinit var model: POIviewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPoiListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(this.context as AppCompatActivity) {
            this.setTitle("Caldas Turística")
        }
        model = ViewModelProvider(requireActivity()).get(POIviewModel::class.java)
        model.getPois()
        model.poisLiveData.observe(viewLifecycleOwner,{
            poiAdapter.setPOIS(it)
        })
        initRecycler()
    }

    private fun POIonClick(poi: POI) {
        //Log.d("onClick", "Click on: $poi")
        model.select(poi)
        findNavController().navigate(R.id.action_POIListFragment_to_POIDetailFragment)
    }

    private fun initRecycler() {
        binding.rvList.layoutManager = LinearLayoutManager(this.context)
        poiAdapter = POIListAdapter() { poi ->
            POIonClick(poi)
        }

        binding.rvList.adapter = poiAdapter
    }

    /*private fun loadData(inFile: String): String {
        var content = ""
        with(this.context as AppCompatActivity) {
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
        val data = loadData("db.json")
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
                    //userJSON.getString("todo")
                )
                Log.d("poi", user.toString())
                poiList.add(user)
            }
            poiAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/
}
