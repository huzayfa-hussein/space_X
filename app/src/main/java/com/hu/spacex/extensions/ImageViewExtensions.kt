package com.hu.spacex.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}