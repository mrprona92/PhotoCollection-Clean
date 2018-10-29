package com.binhth.photocollection.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.StrictMode
import androidx.core.content.ContextCompat
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object PhotoUtils {
    fun setWallpaper(url: String?, context: Context?) {
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

    fun getBitmapFromAsset(context: Context, strName: String): Bitmap? {
        val assetManager = context.assets
        val instr: InputStream?
        return try {
            instr = assetManager.open(strName)
            BitmapFactory.decodeStream(instr)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getColorFromId(context: Context, colorId: Int): Int? {
        return ContextCompat.getColor(context, colorId)
    }

    @Throws(IOException::class)
    fun drawableFromUrl(url: String): Drawable {
        val x: Bitmap

        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val input = connection.inputStream

        x = BitmapFactory.decodeStream(input)
        return BitmapDrawable(x)
    }
}



