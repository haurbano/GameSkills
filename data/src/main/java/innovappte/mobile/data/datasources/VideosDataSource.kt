package innovappte.mobile.data.datasources

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.data.services.DownloadVideoServiceProvider
import innovappte.mobile.data.services.RetrofitClientProvider
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.IOException

class VideosDataSource(
        private val videoPathUtils: VideoPathUtils,
        private val downloadVideoServiceProvider: DownloadVideoServiceProvider
) {

    fun downloadVideo(url: String, targetFileName: String): Completable {
        val newUrl = url.removePrefix(RetrofitClientProvider.BASE_URL)
        if (alreadyDownloaded(targetFileName)) return Completable.complete()

        val downloadVideoService = downloadVideoServiceProvider.get()
        return downloadVideoService.download(newUrl)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable { saveVideo(it, targetFileName) }
                .subscribeOn(Schedulers.io())
                .doOnComplete { buildPreview(targetFileName) }
    }

    private fun saveVideo(response: ResponseBody, targetFileName: String): Completable {
        val input = response.byteStream()
        return Completable.fromAction {
            input.use { input ->
                val videoFile = videoPathUtils.getVideoFile(targetFileName)
                FileOutputStream(videoFile).use { output ->
                    val buffer = ByteArray(4 * 1024)

                    while (true) {
                        val read = input.read(buffer)
                        if (read == -1) {
                            break
                        }
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
            }
        }
    }

    private fun alreadyDownloaded(targetFileName: String): Boolean {
        val file = videoPathUtils.getVideoFile(targetFileName)
        return file?.exists() ?: false
    }

    private fun buildPreview(targetFileName: String?) {
        if (targetFileName.isNullOrBlank()) return
        val previewFile = videoPathUtils.getPreviewFile(targetFileName)
        val videoFile = videoPathUtils.getVideoFile(targetFileName)
        val preview = ThumbnailUtils.createVideoThumbnail(videoFile?.path, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND)
        try {
            FileOutputStream(previewFile).use { out ->
                preview.compress(Bitmap.CompressFormat.PNG, 85, out)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}