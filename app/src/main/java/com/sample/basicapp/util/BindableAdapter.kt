package com.sample.basicapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageSrc")
fun setImage(image: ImageView, imageUrl: String?) {
    if (image!=null&&!imageUrl.isNullOrEmpty()){
        Glide.with(image)
            .load(imageUrl)
            .centerCrop()
            .into(image)
    }
}