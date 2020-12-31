package net.sherafatpour.shop.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageLoader {
    @JvmStatic
    @BindingAdapter("image")
    fun loadImage(view:ImageView , url:String) {
        Glide.with(view).load(url).into(view)
    }

}