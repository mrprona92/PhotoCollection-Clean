package com.binhth.photocollection.ui.screen.photoeditor

import android.os.Environment
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.R
import com.binhth.photocollection.model.FilterItem
import com.binhth.photocollection.model.ToolItem
import com.binhth.photocollection.ui.screen.core.BaseViewModel
import com.binhth.photocollection.ui.screen.photoeditor.tooledit.ToolType
import ja.burhanrashid52.photoeditor.PhotoFilter
import ja.burhanrashid52.photoeditor.SaveSettings
import java.io.File
import java.io.IOException
import java.util.*


class PhotoEditorViewModel : BaseViewModel() {
    val listFilterData = MutableLiveData<ArrayList<FilterItem>>()
    val listToolsData = MutableLiveData<ArrayList<ToolItem>>()
    val saveSettingData = MutableLiveData<SaveSettings>()
    val filePatchData = MutableLiveData<String>()
    var imageUrl = MutableLiveData<String>()
    var isUndoAction = MutableLiveData<Boolean>()
    var obsTitleAction = ObservableField<String>("")

    fun setupFilters() {
        val filterList = arrayListOf<FilterItem>()
        filterList.apply {
            add(FilterItem("Original", "filters/original.webp", PhotoFilter.NONE))
            add(FilterItem("Auto Fix", "filters/auto_fix.webp", PhotoFilter.AUTO_FIX))
            add(FilterItem("Brightness", "filters/brightness.webp", PhotoFilter.BRIGHTNESS))
            add(FilterItem("Contrast", "filters/contrast.webp", PhotoFilter.CONTRAST))
            add(FilterItem("Documentary", "filters/documentary.webp", PhotoFilter.DOCUMENTARY))
            add(FilterItem("Dual tone", "filters/dual_tone.webp", PhotoFilter.DUE_TONE))
            add(FilterItem("Fill Light", "filters/fill_light.webp", PhotoFilter.FILL_LIGHT))
            add(FilterItem("Fish Eye", "filters/fish_eye.webp", PhotoFilter.FISH_EYE))
            add(FilterItem("Grain", "filters/grain.webp", PhotoFilter.GRAIN))
            add(FilterItem("Gray Scale", "filters/gray_scale.webp", PhotoFilter.GRAY_SCALE))
            add(FilterItem("Lomish", "filters/lomish.webp", PhotoFilter.LOMISH))
            add(FilterItem("Negative", "filters/negative.webp", PhotoFilter.NEGATIVE))
            add(FilterItem("Posterize", "filters/posterize.webp", PhotoFilter.POSTERIZE))
            add(FilterItem("Saturate", "filters/saturate.webp", PhotoFilter.SATURATE))
            add(FilterItem("Sepia", "filters/sepia.webp", PhotoFilter.SEPIA))
            add(FilterItem("Sharpen", "filters/sharpen.webp", PhotoFilter.SHARPEN))
            add(FilterItem("Temprature", "filters/temprature.webp", PhotoFilter.TEMPERATURE))
            add(FilterItem("Tint", "filters/tint.webp", PhotoFilter.TINT))
            add(FilterItem("Vignette", "filters/vignette.webp", PhotoFilter.VIGNETTE))
            add(FilterItem("Cross process", "filters/cross_process.webp", PhotoFilter.CROSS_PROCESS))
            add(FilterItem("BNM", "filters/b_n_w.webp", PhotoFilter.BLACK_WHITE))
            add(FilterItem("Flip Horizental", "filters/flip_horizental.webp", PhotoFilter.FLIP_HORIZONTAL))
            add(FilterItem("Flip Vertical", "filters/flip_vertical.webp", PhotoFilter.FLIP_VERTICAL))
            add(FilterItem("Rotate", "filters/rotate.webp", PhotoFilter.ROTATE))
        }
        listFilterData.value?.clear()
        listFilterData.value = filterList
    }

    fun setupTools() {
        val toolList = arrayListOf<ToolItem>()
        toolList.apply {
            add(ToolItem("Brush", R.drawable.ic_brush, ToolType.BRUSH))
            add(ToolItem("Filter", R.drawable.ic_photo_filter, ToolType.FILTER))
            add(ToolItem("Eraser", R.drawable.ic_eraser, ToolType.ERASER))
        }
        listToolsData.value?.clear()
        listToolsData.value = toolList
    }


    fun saveFile() {
        isLoading.value = true
        val file = File(
            Environment.getExternalStorageDirectory().toString()
                    + File.separator + ""
                    + System.currentTimeMillis() + ".png"
        )
        try {
            file.createNewFile()
            val saveSettings = SaveSettings.Builder()
                .setClearViewsEnabled(true)
                .setTransparencyEnabled(true)
                .build()
            saveSettingData.value = saveSettings
            filePatchData.value = file.absolutePath
            isLoading.value = false
        } catch (e: IOException) {
            e.printStackTrace()
            isLoading.value = false
            errorMessage.value = e.message
        }
    }

    fun undoAction() {
        isUndoAction.value = true
    }

    fun redoAction() {
        isUndoAction.value = false
    }
}
