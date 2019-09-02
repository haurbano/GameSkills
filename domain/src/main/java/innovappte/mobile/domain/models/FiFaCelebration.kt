package innovappte.mobile.domain.models

class FiFaCelebration: VideosContainer() {

    var index: Int = 0
    lateinit var id: String
    lateinit var name: Name
    lateinit var celebrationVideo: String
    lateinit var ps4ControlVideo: String
    lateinit var xboxControlVideo: String
    lateinit var actions: List<Action>

    override fun getMainVideo() =
            Video(celebrationVideo, name.default, VideoType.Main)

    override fun getPs4ClassicVideo()=
            Video(ps4ControlVideo, name.default, VideoType.Ps4Classic)

    override fun getPs4AlternativeVideo()=
            Video(ps4ControlVideo, name.default, VideoType.Ps4Alternative)

    override fun getXboxClassicVideo()=
            Video(xboxControlVideo, name.default, VideoType.XboxClassic)

    override fun getXboxAlternativeVideo()=
            Video(xboxControlVideo, name.default, VideoType.XboxAlterative)
}