package com.example.newsapp.recycler2

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadimage")
fun ImageView.loadimage(avatar_url: String?) {
    Glide.with(context).load(avatar_url).into(this)
}