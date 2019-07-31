package innovappte.mobile.domain.models

abstract class VideosContainer {
    fun getVideosToDownloadList(): List<Video> {
        return listOf(
                getMainVideo(),
                getPs4ClassicVideo(),
                getPs4AlternativeVideo(),
                getXboxClassicVideo(),
                getXboxAlternativeVideo()
        )
    }

    abstract fun getMainVideo(): Video
    abstract fun getPs4ClassicVideo(): Video
    abstract fun getPs4AlternativeVideo(): Video
    abstract fun getXboxClassicVideo(): Video
    abstract fun getXboxAlternativeVideo(): Video
}