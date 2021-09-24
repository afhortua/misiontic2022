package com.example.misiontic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.misiontic.databinding.ItemPoiTodoBinding
import com.squareup.picasso.Picasso

class ToDoPOIAdapter (var todo:ArrayList<ToDoPOI>): RecyclerView.Adapter<ToDoPOIAdapter.ToDoHolder>() {


    inner class ToDoHolder(val binding: ItemPoiTodoBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val binding = ItemPoiTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        with(holder){
            with(todo[position]){
                binding.tvName.text=this.name
                binding.tvTodo.text=this.todo
                //Picasso.get().load(this.image).into(binding.ivPoi)
            }
        }
    }

    override fun getItemCount(): Int = todo.size
}
