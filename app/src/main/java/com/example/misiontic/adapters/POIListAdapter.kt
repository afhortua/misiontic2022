package com.example.misiontic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.misiontic.models.POI
import com.example.misiontic.R
import com.example.misiontic.UI.POIDetailFragment
import com.example.misiontic.databinding.ItemPoiListBinding
import com.squareup.picasso.Picasso

class POIListAdapter(
    private val onClick: (POI) -> Unit
) : RecyclerView.Adapter<POIListAdapter.PoiHolder>() {

    private var poiList: ArrayList<POI> = arrayListOf()

    inner class PoiHolder(val binding: ItemPoiListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiHolder {
        val binding = ItemPoiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoiHolder(binding)
    }

    override fun onBindViewHolder(holder: PoiHolder, position: Int) {
        holder.itemView.setOnClickListener() {
            onClick(poiList[position])
        }
        with(holder) {
            //se reemplazan los datos del POI en el layout
            with(poiList[position]) {
                binding.tvName.text = this.name
                binding.tvDescription.text = this.description
                binding.tvLocation.text = this.location
                Picasso.get().load(this.image).into(binding.ivPoi)
            }
        }
    }

    override fun getItemCount(): Int = poiList.size

    //Usada para cargar los POI obtenidos del servicio web desde ListFragment en initRecycler

    fun setPOIS(pois: ArrayList<POI>?) {
        this.poiList.clear()
        pois?.let {
            this.poiList.addAll(it)
        }
        notifyDataSetChanged()
    }
}
