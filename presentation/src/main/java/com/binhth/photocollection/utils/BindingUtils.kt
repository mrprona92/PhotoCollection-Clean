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
import ja.burhanrashid52.photoeditor.PhotoEditorView


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

@BindingAdapter(
    value = ["loadImagePhotoEditor", "placeholder", "fitCenter", "centerCrop"],
    requireAll = false
)
fun PhotoEditorView.source(url: String? = "", placeholder: Drawable?, fitCenter: Boolean?, centerCrop: Boolean?) {
    val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (TextUtils.isEmpty(url)) {
        source.setImageDrawable(placeholder)
    } else {
        placeholder?.let { options.placeholder(it) }
        fitCenter?.apply { if (fitCenter) options.fitCenter() }
        centerCrop?.apply { if (centerCrop) options.centerCrop() }
        Glide.with(context).load(url).apply(options).into(source)
    }
}

@BindingAdapter(
    value = ["imageDrawable", "imageAssert"],
    requireAll = false
)
fun ImageView.setImageViewResource(imageDrawable: Int? = 0, imageAssert: String?) {
    if (!TextUtils.isEmpty(imageAssert)) {
        imageAssert?.let {
            val fromAsset = PhotoUtils.getBitmapFromAsset(this.context, it)
            this.setImageBitmap(fromAsset)
        }
    } else {
        imageDrawable?.let { this.setImageResource(it) }
    }
}


@BindingAdapter("viewBackground")
fun setBackgroundColor(view: View, colorRes: Int? = 0) {
    colorRes?.let {
        view.setBackgroundColor(PhotoUtils.getColorFromId(view.context, it) ?: 0)
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


