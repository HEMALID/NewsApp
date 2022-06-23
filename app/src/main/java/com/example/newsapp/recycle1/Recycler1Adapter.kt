package com.example.newsapp.recycle1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.Layout1Binding


class Recycler1Adapter(var context: Context,var mNews : newsAction):RecyclerView.Adapter<Recycler1Adapter.ViewHolder>() {

    interface newsAction{
        fun onClick(n: String)
    }

    var selectedPosition = -1

    private val img = arrayOf(R.drawable.global,
        R.drawable.document_text,
        R.drawable.flash,
        R.drawable.flash,
        R.drawable.flash)
    var text = arrayOf("All","Science","Startups","Technology","Business")

    inner class ViewHolder(var binding: Layout1Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=Layout1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cIv.setImageResource(img[position])
        holder.binding.tvTitle.text = text[position]
        holder.itemView.setOnClickListener {

            mNews.onClick(text[position])
            selectedPosition = position
            notifyDataSetChanged()
        }
        if(selectedPosition != position) {
            holder.binding.cIv.background = context.getDrawable(R.drawable.item_not_selected)
        }else {
            holder.binding.cIv.background = context.getDrawable(R.drawable.item_selected)
        }
    }

    override fun getItemCount(): Int {
        return text.size
    }

}


