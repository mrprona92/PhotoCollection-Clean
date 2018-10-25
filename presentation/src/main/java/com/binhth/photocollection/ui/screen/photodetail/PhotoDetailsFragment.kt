package com.binhth.photocollection.ui.screen.photodetail

import android.Manifest
import android.os.Bundle
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.lifecycle.Observer
import com.binhth.photocollection.BR
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.FragmentPhotoDetailsBinding
import com.binhth.photocollection.ui.screen.core.BaseFragment
import com.binhth.photocollection.utils.DialogUtils
import com.binhth.photocollection.utils.PhotoUtils
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotoDetailsFragment : BaseFragment<FragmentPhotoDetailsBinding, PhotoDetailsViewModel>() {

    companion object {
        const val TAG = "PhotoDetailsFragment"

        const val PHOTO_URL = "photoUrl"
        const val PHOTO_ID = "id"

        fun newInstance(id: String?, photoUrl: String?): PhotoDetailsFragment {
            val args = Bundle()
            args.putString(PHOTO_ID, id)
            args.putString(PHOTO_URL, photoUrl)
            val fragment = PhotoDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<PhotoDetailsViewModel>()

    private lateinit var floatingActionMenu: FloatingActionMenu
    private lateinit var seekBar: AppCompatSeekBar

    override fun initContent(viewBinding: FragmentPhotoDetailsBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.apply {
            floatingActionMenu = floatMenu
            seekBar = seekbar
            seekBar.isEnabled = false
            floatingActionMenu.setClosedOnTouchOutside(true)
            floatingActionMenu.hideMenuButton(false)
            initFabMenu(floatingActionMenu)
            floatingActionMenu.showMenuButton(true)
        }

        viewModel.apply {
            imageUrl.value = arguments?.getString(PHOTO_URL)
            imageId.value = arguments?.getString(PHOTO_ID)
            progressDownload.observe(this@PhotoDetailsFragment, Observer {
                seekBar.progress = it
                when (it) {
                    0 -> DialogUtils.showToast(mainActivity, getString(R.string.details_image_download_started))
                }
            })
            filePathSaved.observe(this@PhotoDetailsFragment, Observer {
                DialogUtils.showToast(mainActivity, getString(R.string.details_image_download_complete, it))
            })
        }
        activity?.title = tag
        mainActivity.showBackButton(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoading.removeObservers(this)
        viewModel.progressDownload.removeObservers(this)
        viewModel.filePathSaved.removeObservers(this)
        mainActivity.showBackButton(false)
    }

    override val layoutId: Int
        get() = R.layout.fragment_photo_details


    private fun initFabMenu(floatingActionMenu: FloatingActionMenu) {
        val downloadFab = FloatingActionButton(activity)
        downloadFab.buttonSize = FloatingActionButton.SIZE_MINI
        downloadFab.labelText = getString(R.string.details_image_download)
        downloadFab.setImageResource(R.drawable.ic_file_download_black_24dp)
        downloadFab.setOnClickListener { _ ->
            // Must be done during an initialization phase like onCreate
            rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) {
                        viewModel.apply { downloadFile(imageId.value, imageUrl.value) }
                    } else {
                        DialogUtils.showToast(mainActivity, getString(R.string.request_permission_false))
                    }
                }
        }

        val setWallpaperFab = FloatingActionButton(activity)
        setWallpaperFab.buttonSize = FloatingActionButton.SIZE_MINI
        setWallpaperFab.labelText = getString(R.string.details_image_set_wallpaper)
        setWallpaperFab.setImageResource(R.drawable.ic_wallpaper_black_24dp)
        setWallpaperFab.setOnClickListener { _ ->
            rxPermissions
                .request(Manifest.permission.SET_WALLPAPER)
                .subscribe { granted ->
                    if (granted) {
                        PhotoUtils.setWallpaper(arguments?.getString(PhotoDetailsFragment.PHOTO_URL), context)
                    } else {
                        DialogUtils.showToast(mainActivity, getString(R.string.request_permission_false))
                    }
                }
        }
        floatingActionMenu.addMenuButton(downloadFab)
        floatingActionMenu.addMenuButton(setWallpaperFab)
    }
}