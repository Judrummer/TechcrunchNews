package com.github.judrummer.techcrunchnews.extension

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by judrummer on 19/5/2560.
 */


fun ImageView.setImageUrl(url: String, @DrawableRes placeholderResId: Int = -1) {
    Glide.with(context)
            .load(url)
            .apply {
                if (placeholderResId != -1) placeholder(placeholderResId)
            }
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .crossFade()
            .into(this)
}
