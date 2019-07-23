package innovappte.mobile.domain.models

import java.io.Serializable

class GameSkill: Serializable {
    var index: Int = 0
    lateinit var name: SkillName
    lateinit var ps4ControlAlternativeVideo: String
    lateinit var ps4ControlClassicVideo: String
    var skillMoves: Int = 0
    lateinit var skillVideo: String
    lateinit var xboxControlAlternativeVideo: String
    lateinit var xboxControlClassicVideo: String
    lateinit var actions: List<Action>
}