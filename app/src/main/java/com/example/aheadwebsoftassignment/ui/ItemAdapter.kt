package com.example.aheadwebsoftassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.aheadwebsoftassignment.data.Menu
import com.example.aheadwebsoftassignment.databinding.ItemBinding
import com.example.aheadwebsoftassignment.others.Utils

class ItemAdapter(private val list: List<Menu>): RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    private var seeMore = false
    private lateinit var context : Context

    fun seeMoreClick() {
        seeMore = !seeMore
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (list.size > 4){
            if (seeMore){
                return list.size
            }else return 4
        }else return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.binding.apply {
            if (position % 2 == 0){
                val params = labelCL.layoutParams as ViewGroup.MarginLayoutParams
                params.marginEnd = 10
                labelCL.layoutParams = params
            }else{
                val params = labelCL.layoutParams as ViewGroup.MarginLayoutParams
                params.marginStart = 10
                labelCL.layoutParams = params
            }

            if (position > 1){
                val params = labelCL.layoutParams as ViewGroup.MarginLayoutParams
                params.topMargin = 10
                labelCL.layoutParams = params
            }

            Utils.loadImage(
                context = context,
                imageView = labelIV,
                url = data.icon,
                defaultResId = data.defaultImage
            )

            labelTV.text = data.label
        }
    }
}