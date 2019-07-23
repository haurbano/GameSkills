package com.hamilton.gamesskillst.domain.models

import innovappte.mobile.domain.models.GameSkill

data class GameSkillsResponse(val skills: List<GameSkill>) {

    enum class VideoSkillsDownloadStatus {
        Completed, Started
    }
}