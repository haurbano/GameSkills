package com.hamilton.gamesskillst.data

import com.google.gson.Gson
import com.hamilton.gamesskillst.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import com.hamilton.gamesskillst.domain.repositories.GameSkillsRepository

class GameSkillRepositoryImpl(
    private val dataFilesManager: DataFilesManager
) : GameSkillsRepository {
    companion object{
        const val GAME_SKILLS_FILE_NAME = "game_skill_example.json"
    }

    override fun getGameSkills(): List<GameSkill> {
        val jsonString = dataFilesManager.getAssetAsString(GAME_SKILLS_FILE_NAME)
        return Gson().fromJson(jsonString, GameSkillsResponse::class.java).skills
    }
}