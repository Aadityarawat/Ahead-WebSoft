package com.example.aheadwebsoftassignment.others

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.aheadwebsoftassignment.R

class Utils {
    companion object{
        fun loadImage(context: Context, imageView: ImageView, url: String?, defaultResId: Int?) {
            if (url == " " || url.isNullOrEmpty()) {
                val defaultImage = defaultResId ?: R.drawable.signal
                Glide.with(context)
                    .load(defaultImage)
                    .placeholder(R.drawable.signal)
                    .error(R.drawable.signal)
                    .into(imageView)
            } else {
                Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.signal)
                    .error(R.drawable.signal)
                    .into(imageView)
            }
        }
    }
}