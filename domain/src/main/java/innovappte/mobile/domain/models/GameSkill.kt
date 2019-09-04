package innovappte.mobile.domain.models

import java.io.Serializable

class GameSkill: VideosContainer(), Serializable {

    var index: Int = 0
    lateinit var name: Name
    lateinit var ps4ControlAlternativeVideo: String
    lateinit var ps4ControlClassicVideo: String
    var skillMoves: Int = 0
    lateinit var skillVideo: String
    lateinit var xboxControlAlternativeVideo: String
    lateinit var xboxControlClassicVideo: String
    lateinit var actions: List<Action>

    override fun getMainVideo() =
            Video(skillVideo, name.default, VideoType.Main)

    override fun getPs4ClassicVideo() =
            Video(ps4ControlClassicVideo, name.default, VideoType.Ps4Classic)

    override fun getPs4AlternativeVideo() =
            Video(ps4ControlAlternativeVideo, name.default, VideoType.Ps4Alternative)

    override fun getXboxClassicVideo() =
            Video(xboxControlClassicVideo, name.default, VideoType.XboxClassic)

    override fun getXboxAlternativeVideo() =
            Video(xboxControlAlternativeVideo, name.default, VideoType.XboxAlterative)
}