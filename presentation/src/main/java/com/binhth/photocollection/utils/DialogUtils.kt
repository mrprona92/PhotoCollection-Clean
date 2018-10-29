package com.binhth.photocollection.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.binhth.photocollection.R

object DialogUtils {
    fun showToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showSaveDialog(context: Context, saveEditListener: SaveEditListener) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(context.getString(R.string.dialog_quest_save_image))
        builder.setPositiveButton(context.getString(R.string.dialog_save)) { _, _ -> saveEditListener.saveImage() }
        builder.setNegativeButton(
            context.getString(R.string.dialog_cancel)
        ) { dialog, _ -> dialog.dismiss() }

        builder.setNeutralButton(context.getString(R.string.dialog_discard)) { _, _ -> saveEditListener.finish() }
        builder.create().show()
    }

    interface SaveEditListener {
        fun saveImage()
        fun finish()
    }
}
