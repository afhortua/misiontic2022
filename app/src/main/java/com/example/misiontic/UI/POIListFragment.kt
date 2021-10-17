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
    private lateinit var poiList : ArrayList<POI>

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
            //se carga en el adapter todos los poi del viewModel
            poiAdapter.setPOIS(it)
        })
        initRecycler()
    }

    private fun POIonClick(poi: POI) {
        //al hacer click se carga el poi seleccionado en el viewModel y se abre el DetailFragment
        model.select(poi)
        findNavController().navigate(R.id.action_POIListFragment_to_POIDetailFragment)
    }

    private fun initRecycler() {
        //se inicializa el RecyclerView
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
                val poiJSON = poiJSON.getJSONObject(i)
                val poi = POI(
                    poiJSON.getString("image"),
                    poiJSON.getString("name"),
                    poiJSON.getString("description"),
                    poiJSON.getString("location"),
                    poiJSON.getString("detail"),
                    poiJSON.getString("temperature")
                )
                Log.d("poi", poi.toString())
                poiList.add(poi)
            }
            poiAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/
}
