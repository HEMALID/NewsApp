package com.example.newsapp.recycler2

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsDetailsActivity
import com.example.newsapp.databinding.Layout2Binding
import com.example.newsapp.recycler2.model.DataX


class Recycler2Adapter (var context: Context, var news: List<DataX>,var acivity:Activity): RecyclerView.Adapter<Recycler2Adapter.ViewHolder>() {


    inner class ViewHolder(var binding: Layout2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataX) {
            binding.news=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=Layout2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(news[position])
        holder.binding.tvRead.append("Read Now")

        val text="Read Now"
        val ss=SpannableString(text)
        val clickableSpan: ClickableSpan=object : ClickableSpan() {
            override fun onClick(textView: View) {
                val intent=Intent(context, NewsDetailsActivity::class.java)
                intent.putExtra("news",news[position])
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                acivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                 context.startActivity(intent)
            }
        }




        ss.setSpan(clickableSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(StyleSpan(Typeface.BOLD), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(ForegroundColorSpan(Color.BLACK), 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        holder.binding.tvRead.text=ss
        holder.binding.tvRead.movementMethod=LinkMovementMethod.getInstance()




    }

    override fun getItemCount(): Int {
        return news.size
    }
}