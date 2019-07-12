package com.hamilton.gamesskillst.domain.models

data class GameSkillsResponse(val skills: List<GameSkill>) {

    enum class VideoSkillsDownloadStatus {
        Completed, Started
    }
}