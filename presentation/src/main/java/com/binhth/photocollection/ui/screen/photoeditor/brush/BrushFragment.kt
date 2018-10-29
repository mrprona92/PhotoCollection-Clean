package com.binhth.photocollection.ui.screen.photoeditor.brush

import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.binhth.photocollection.BR
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.FragmentBottomPropertiesDialogBinding
import com.binhth.photocollection.model.ColorPickerItem
import com.binhth.photocollection.ui.screen.core.BaseBottomFragment
import com.binhth.photocollection.ui.screen.photoeditor.brush.colorpicker.ColorPickerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class BrushFragment : BaseBottomFragment<FragmentBottomPropertiesDialogBinding, BrushViewModel>(),
    SeekBar.OnSeekBarChangeListener {

    lateinit var properties: Properties

    interface Properties {
        fun onColorChanged(colorCode: Int)

        fun onOpacityChanged(opacity: Int)

        fun onBrushSizeChanged(brushSize: Int)
    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar?.id) {
            R.id.seekbar_opacity -> properties.onOpacityChanged(progress)
            R.id.seekbar_size -> properties.onBrushSizeChanged(progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<BrushViewModel>()


    override fun initContent(viewBinding: FragmentBottomPropertiesDialogBinding) {
        viewBinding.viewModel = viewModel
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val colorPickerAdapter = ColorPickerAdapter(itemClick = {
            this@BrushFragment.dismiss()
            properties.onColorChanged(it.backgroundColor)
        })
        viewBinding.apply {
            val seekBarOpacity = seekbarOpacity
            val seekBarBrushSize = seekbarSize
            seekBarOpacity.setOnSeekBarChangeListener(this@BrushFragment)
            seekBarBrushSize.setOnSeekBarChangeListener(this@BrushFragment)
        }

        viewBinding.recyclerViewColor.apply {
            this.layoutManager = layoutManager
            adapter = colorPickerAdapter
            setHasFixedSize(true)
        }

        viewModel.apply {
            setupColorsData()
            listColorsData.observe(this@BrushFragment, Observer {
                val list = ArrayList<ColorPickerItem>()
                list.addAll(it)
                colorPickerAdapter.submitList(list)
            })
        }
        activity?.title = tag
    }

    fun setPropertiesChangeListener(properties: Properties) {
        this.properties = properties
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoading.removeObservers(this)
    }

    override val layoutId: Int
        get() = R.layout.fragment_bottom_properties_dialog
}