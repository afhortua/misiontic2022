package com.example.misiontic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.misiontic.databinding.ItemPoiListBinding
import com.squareup.picasso.Picasso

class POIListAdapter (var poi:ArrayList<POI>): RecyclerView.Adapter<POIListAdapter.PoiHolder>() {

    inner class PoiHolder(val binding: ItemPoiListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiHolder {
        val binding = ItemPoiListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PoiHolder(binding)
    }

    override fun onBindViewHolder(holder: PoiHolder, position: Int) {
        holder.itemView.setOnClickListener(){
            val act=it.context as AppCompatActivity
            act.supportFragmentManager.beginTransaction().replace(R.id.frag,POIDetailFragment(poi[position].id)).addToBackStack(null).commit()
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
