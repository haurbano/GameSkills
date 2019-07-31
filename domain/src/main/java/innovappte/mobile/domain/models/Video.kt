package innovappte.mobile.domain.models

open class Video(val url: String, val name: String, val videoType: VideoType) {
    fun getTargetFileName() = "${videoType.name}_$name"

    class Empty: Video("", "", VideoType.Main)
}