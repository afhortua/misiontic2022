package com.example.misiontic

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.misiontic.databinding.ActivityMainBinding
import com.example.misiontic.databinding.ItemPoiBinding
import com.squareup.picasso.Picasso

class PoiAdapter (var poi:ArrayList<POI>): RecyclerView.Adapter<PoiAdapter.PoiHolder>() {

    inner class PoiHolder(val binding: ItemPoiBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiHolder {
        val binding = ItemPoiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PoiHolder(binding)
    }

    override fun onBindViewHolder(holder: PoiHolder, position: Int) {
        holder.itemView.setOnClickListener(){
            val act=it.context as AppCompatActivity
            act.supportFragmentManager.beginTransaction().replace(R.id.frag,POIDetailFragment()).addToBackStack(null).commit()
        }
        with(holder){
            with(poi[position]){
                binding.tvName.text=this.name
                binding.tvDescription.text=this.description
                binding.tvLocation.text=this.location
                Picasso.get().load(this.image).into(binding.ivPoi)
            }
        }
    }

    override fun getItemCount(): Int = poi.size
}
