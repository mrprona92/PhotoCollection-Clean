package com.binhth.photocollection.ui.screen.photoeditor.brush

import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.R
import com.binhth.photocollection.model.ColorPickerItem
import com.binhth.photocollection.ui.screen.core.BaseViewModel


class BrushViewModel : BaseViewModel() {

    val listColorsData = MutableLiveData<ArrayList<ColorPickerItem>>()

    fun setupColorsData() {
        val colorList = arrayListOf<ColorPickerItem>()
        colorList.add(ColorPickerItem(R.color.blue_color_picker))
        colorList.add(ColorPickerItem(R.color.brown_color_picker))
        colorList.add(ColorPickerItem(R.color.green_color_picker))
        colorList.add(ColorPickerItem(R.color.orange_color_picker))
        colorList.add(ColorPickerItem(R.color.red_color_picker))
        colorList.add(ColorPickerItem(R.color.black))
        colorList.add(ColorPickerItem(R.color.red_orange_color_picker))
        colorList.add(ColorPickerItem(R.color.sky_blue_color_picker))
        colorList.add(ColorPickerItem(R.color.violet_color_picker))
        colorList.add(ColorPickerItem(R.color.white))
        colorList.add(ColorPickerItem(R.color.yellow_color_picker))
        colorList.add(ColorPickerItem(R.color.yellow_green_color_picker))
        listColorsData.value?.clear()
        listColorsData.value = colorList
    }
}
