package innovappte.mobile.data.datasources

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import innovappte.mobile.common.L
import innovappte.mobile.data.VideoPathUtils

class VideosDataSource(
        private val context: Context,
        private val videoPathUtils: VideoPathUtils
) {

    fun downloadVideo(url: String, targetFileName: String) {
        if (alreadyDownloaded(targetFileName)) return

        L.i("Downloading new video - $targetFileName")
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
        val request = buildRequest(url, targetFileName)
        if (request != null) {
            downloadManager?.enqueue(request)
        }
    }

    private fun buildRequest(url: String, videoName: String): DownloadManager.Request? {
        val uri = Uri.parse(url)
        val videoFile = videoPathUtils.getVideoFile(videoName)
        val destinationUri = Uri.fromFile(videoFile)
        return try {
            DownloadManager.Request(uri).setDestinationUri(destinationUri)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    private fun alreadyDownloaded(targetFileName: String): Boolean {
        val file = videoPathUtils.getVideoFile(targetFileName)
        return file?.exists() ?: false
    }
}