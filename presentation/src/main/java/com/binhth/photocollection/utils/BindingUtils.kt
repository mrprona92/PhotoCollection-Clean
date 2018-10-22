package com.binhth.photocollection.utils

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object Constant {
    const val THRESHOLD_CLICK_TIME = 1200
}

@BindingAdapter(
    value = ["loadImage", "placeholder", "fitCenter", "centerCrop"],
    requireAll = false
)
fun ImageView.setImageUrl(url: String? = "", placeholder: Drawable?, fitCenter: Boolean?, centerCrop: Boolean?) {
    val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (TextUtils.isEmpty(url)) {
        setImageDrawable(placeholder)
    } else {
        placeholder?.let { options.placeholder(it) }
        fitCenter?.apply { if (fitCenter) options.fitCenter() }
        centerCrop?.apply { if (centerCrop) options.centerCrop() }
        Glide.with(context).load(url).apply(options).into(this)
    }
}

@BindingAdapter("clickSafe")
fun setClickSafe(view: View, listener: View.OnClickListener?) {
    view.setOnClickListener(object : View.OnClickListener {
        private var mLastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < Constant.THRESHOLD_CLICK_TIME) {
                return
            }
            listener?.onClick(v)
            mLastClickTime = SystemClock.elapsedRealtime()
        }
    })
}


