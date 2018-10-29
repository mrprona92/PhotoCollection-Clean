package com.binhth.photocollection.ui.screen.photoeditor

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.binhth.photocollection.BR
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.FragmentPhotoEditorBinding
import com.binhth.photocollection.model.FilterItem
import com.binhth.photocollection.model.ToolItem
import com.binhth.photocollection.ui.screen.core.BaseFragment
import com.binhth.photocollection.ui.screen.photoeditor.brush.BrushFragment
import com.binhth.photocollection.ui.screen.photoeditor.filter.FilterPhotoAdapter
import com.binhth.photocollection.ui.screen.photoeditor.tooledit.ToolEditAdapter
import com.binhth.photocollection.ui.screen.photoeditor.tooledit.ToolType
import com.binhth.photocollection.utils.DialogUtils
import io.reactivex.annotations.NonNull
import ja.burhanrashid52.photoeditor.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


class PhotoEditorFragment : BaseFragment<FragmentPhotoEditorBinding, PhotoEditorViewModel>(), OnPhotoEditorListener,
    DialogUtils.SaveEditListener, BrushFragment.Properties {
    override fun onColorChanged(colorCode: Int) {
        photoEditor.brushColor = colorCode
    }

    override fun onOpacityChanged(opacity: Int) {
        photoEditor.setOpacity(opacity)
    }

    override fun onBrushSizeChanged(brushSize: Int) {
        photoEditor.brushSize = brushSize.toFloat()
    }

    companion object {
        const val TAG = "PhotoEditorFragment"
        const val PHOTO_URL = "photoUrl"

        fun newInstance(photoUrl: String?) = PhotoEditorFragment().apply {
            arguments = Bundle().apply {
                putString(PHOTO_URL, photoUrl)
            }
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<PhotoEditorViewModel>()
    private lateinit var photoEditor: PhotoEditor
    lateinit var photoEditorView2: PhotoEditorView
    private lateinit var brushFragment: BrushFragment

    private var isFilterVisible: Boolean = false
    private val constraintSet = ConstraintSet()
    private lateinit var recyclerViewTools: RecyclerView
    private lateinit var recyclerViewFilters: RecyclerView
    private lateinit var mainView: ConstraintLayout

    override fun initContent(viewBinding: FragmentPhotoEditorBinding) {
        brushFragment = BrushFragment()
        brushFragment.setPropertiesChangeListener(this)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManagerTool = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listFilterAdapter = FilterPhotoAdapter(itemClick = {
            onFilterSelected(it.filterImage)
        })
        val listToolAdapter = ToolEditAdapter(itemClick = {
            when (it.toolType) {
                ToolType.FILTER -> showFilter(true)
                ToolType.BRUSH -> showBrush()
                ToolType.ERASER -> showEraser()
                else -> {
                    TODO()
                }
            }
            viewModel.obsTitleAction.set(it.toolName)
        })
        viewBinding.viewModel = viewModel
        viewBinding.apply {
            recyclerViewTools = rvConstraintTools
            recyclerViewFilters = rvFilterView
            mainView = rootView
            photoEditorView2 = photoEditorView
            rvFilterView.apply {
                photoEditor = PhotoEditor.Builder(context, photoEditorView2)
                    .setPinchTextScalable(true)
                    .build()
                photoEditor.setOnPhotoEditorListener(this@PhotoEditorFragment)
                layoutManager = linearLayoutManager
                adapter = listFilterAdapter
            }
            rvConstraintTools.apply {
                layoutManager = linearLayoutManagerTool
                adapter = listToolAdapter
            }
        }
        viewModel.apply {
            imageUrl.value = arguments?.getString(PHOTO_URL)
            setupFilters()
            setupTools()
            listFilterData.observe(this@PhotoEditorFragment, Observer {
                val list = ArrayList<FilterItem>()
                list.addAll(it)
                listFilterAdapter.submitList(list)
            })

            listToolsData.observe(this@PhotoEditorFragment, Observer {
                val list = ArrayList<ToolItem>()
                list.addAll(it)
                listToolAdapter.submitList(list)
            })

            isUndoAction.observe(this@PhotoEditorFragment, Observer {
                when (isUndoAction.value) {
                    true -> photoEditor.undo()
                    false -> photoEditor.redo()
                }
            })

            saveSettingData.observe(this@PhotoEditorFragment, Observer {
                photoEditor.saveAsFile(filePatchData.value ?: "", it, object : PhotoEditor.OnSaveListener {
                    override fun onSuccess(@NonNull imagePath: String) {
                        photoEditorView2.source.setImageURI(Uri.fromFile(File(imagePath)))
                        DialogUtils.showToast(mainActivity, getString(R.string.details_image_save_complete, imagePath))
                    }

                    override fun onFailure(@NonNull exception: Exception) {
                        DialogUtils.showToast(mainActivity, exception.message)
                    }
                })
            })
        }
        activity?.title = tag
        mainActivity.showBackButton(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoading.removeObservers(this)
        viewModel.isUndoAction.removeObservers(this)
        viewModel.listToolsData.removeObservers(this)
        viewModel.imageUrl.removeObservers(this)
        viewModel.saveSettingData.removeObservers(this)
        mainActivity.showBackButton(false)
    }

    override val layoutId: Int
        get() = R.layout.fragment_photo_editor


    override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {
    }

    override fun onStartViewChangeListener(viewType: ViewType?) {
    }

    override fun onRemoveViewListener(numberOfAddedViews: Int) {
    }

    override fun onRemoveViewListener(viewType: ViewType?, numberOfAddedViews: Int) {
    }

    override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {
    }

    override fun onStopViewChangeListener(viewType: ViewType?) {
    }

    private fun onFilterSelected(photoFilter: PhotoFilter) {
        photoEditor.setFilterEffect(photoFilter)
    }


    private fun showBrush() {
        photoEditor.setBrushDrawingMode(true)
        brushFragment.show(mainActivity.supportFragmentManager, brushFragment.tag)
    }

    private fun showEraser() {
        photoEditor.brushEraser()
    }


    private fun showFilter(isVisible: Boolean) {
        isFilterVisible = isVisible
        constraintSet.clone(mainView)
        if (isVisible) {
            constraintSet.clear(recyclerViewFilters.id, ConstraintSet.START)
            constraintSet.connect(
                recyclerViewFilters.id, ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.START
            )
            constraintSet.connect(
                recyclerViewFilters.id, ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END
            )
        } else {
            constraintSet.connect(
                recyclerViewFilters.id, ConstraintSet.START,
                ConstraintSet.PARENT_ID, ConstraintSet.END
            )
            constraintSet.clear(recyclerViewFilters.id, ConstraintSet.END)
        }

        val changeBounds = ChangeBounds()
        changeBounds.duration = 350
        changeBounds.interpolator = AnticipateOvershootInterpolator(1.0f)
        TransitionManager.beginDelayedTransition(mainView, changeBounds)

        constraintSet.applyTo(mainView)
    }

    override fun onBackPressed(): Boolean {
        if (isFilterVisible) {
            showFilter(false)
        } else if (!photoEditor.isCacheEmpty) {
            DialogUtils.showSaveDialog(mainActivity, this@PhotoEditorFragment)
        } else {
            finish()
        }
        return true
    }

    override fun saveImage() {
        rxPermissions
            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe { granted ->
                if (granted) {
                    viewModel.saveFile()
                } else {
                    DialogUtils.showToast(mainActivity, getString(R.string.request_permission_false))
                }
            }
    }

    override fun finish() {
        fragmentManager?.popBackStackImmediate()
    }
}