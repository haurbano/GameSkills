package com.hamilton.gamesskillst.domain.models

import java.io.Serializable

data class GameSkill(
    val index: Int,
    val name: SkillName,
    val ps4ControlAlternativeVideo: String,
    val ps4ControlClassicVideo: String,
    val skillMoves: Int,
    val skillVideo: String,
    val xboxControlAlternativeVideo: String,
    val xboxControlClassicVideo: String,
    val actions: List<Action>
) : Serializable