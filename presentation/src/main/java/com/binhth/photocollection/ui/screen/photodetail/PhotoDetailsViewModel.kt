package com.binhth.photocollection.ui.screen.photodetail

import android.os.Environment
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.binhth.photocollection.ui.screen.core.BaseViewModel
import com.tonyodev.fetch2.*
import com.tonyodev.fetch2core.DownloadBlock
import org.jetbrains.annotations.NotNull
import java.io.File


class PhotoDetailsViewModel constructor(private val fetch: Fetch) : BaseViewModel() {
    val filePathSaved = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val imageId = MutableLiveData<String>()
    var progressDownload = MutableLiveData<Int>()
    var progressDownloadText = ObservableField(0)
    var isDislayDownload = ObservableField(false)

    fun downloadFile(id: String?, url: String?) {
        val path = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        val file = File(path, "$id.jpg")
        val request = Request(url ?: "", file.absolutePath)
        isDislayDownload.set(true)
        fetch.enqueue(request, null, null)
        fetch.addListener(object : FetchListener {
            override fun onAdded(download: Download) {
            }

            override fun onDownloadBlockUpdated(download: Download, downloadBlock: DownloadBlock, totalBlocks: Int) {
            }

            override fun onStarted(download: Download, downloadBlocks: List<DownloadBlock>, totalBlocks: Int) {
            }

            override fun onWaitingNetwork(download: Download) {
            }

            override fun onError(download: Download, error: Error, throwable: Throwable?) {
                isDislayDownload.set(false)
            }

            override fun onQueued(@NotNull download: Download, waitingOnNetwork: Boolean) {
            }

            override fun onCompleted(@NotNull download: Download) {
                filePathSaved.value = file.absolutePath
                isDislayDownload.set(false)
            }

            override fun onProgress(
                @NotNull download: Download, etaInMilliSeconds: Long,
                downloadedBytesPerSecond: Long
            ) {
                progressDownload.value = download.progress
                progressDownloadText.set(download.progress)
            }

            override fun onPaused(@NotNull download: Download) {

            }

            override fun onResumed(@NotNull download: Download) {

            }

            override fun onCancelled(@NotNull download: Download) {

            }

            override fun onRemoved(@NotNull download: Download) {

            }

            override fun onDeleted(@NotNull download: Download) {

            }
        })
    }
}


