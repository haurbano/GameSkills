package innovappte.mobile.data

import android.content.Context
import android.net.Uri
import android.util.Log
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.domain.models.VideosContainer
import java.io.File

class VideoPathUtils(private val context: Context) {

    fun getVideoFile(videoContainer: VideosContainer, videoType: VideoType): File? {
        return when (videoType) {
            VideoType.Main -> getVideoFile(videoContainer.getMainVideo().getTargetFileName())
            VideoType.Ps4Alternative ->  getVideoFile(videoContainer.getPs4AlternativeVideo().getTargetFileName())
            VideoType.Ps4Classic ->  getVideoFile(videoContainer.getPs4ClassicVideo().getTargetFileName())
            VideoType.XboxClassic ->  getVideoFile(videoContainer.getXboxClassicVideo().getTargetFileName())
            VideoType.XboxAlterative ->  getVideoFile(videoContainer.getXboxAlternativeVideo().getTargetFileName())
        }
    }

    fun getVideoFile(videoTargetFileName: String): File? {
        val folder = context.filesDir
        return File(folder, videoTargetFileName)
    }

    fun getVideoPreviewUri(videoContainer: VideosContainer): Uri {
        val previewFile = getPreviewFile(videoContainer.getMainVideo().getTargetFileName())
        return Uri.fromFile(previewFile).also { Log.i("--haur", "VideoPreview: ${it.path}") }
    }

    fun getPreviewFile(videoTargetFileName: String): File? {
        val folder = context.filesDir
        return File(folder, "${videoTargetFileName}_preview")
    }


}