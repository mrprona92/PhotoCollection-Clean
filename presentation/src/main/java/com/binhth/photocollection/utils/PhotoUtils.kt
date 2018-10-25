package com.binhth.photocollection.utils

import android.app.WallpaperManager
import android.content.Context
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.net.URL

object PhotoUtils {
    open fun setWallpaper(url: String?, context: Context?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val wpm = WallpaperManager.getInstance(context)
        val ins: InputStream?
        try {
            ins = URL(url).openStream()
            wpm.setStream(ins)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}


