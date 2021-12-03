package com.example.airqualitywebsocket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualitywebsocket.R
import com.example.airqualitywebsocket.databinding.ItemListBinding
import com.example.airqualitywebsocket.pojo.MData

class AiqListAdapter(private val aiqList: ArrayList<MData>) :
    RecyclerView.Adapter<AiqListAdapter.ViewHolder>() {

    class ViewHolder(internal val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            data = aiqList[position]
        }
    }

    override fun getItemCount() = aiqList.size
}