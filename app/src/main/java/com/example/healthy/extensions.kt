package com.example.healthy

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

//To get image using url
fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}