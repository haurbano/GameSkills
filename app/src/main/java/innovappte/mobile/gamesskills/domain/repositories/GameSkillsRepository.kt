package com.hamilton.gamesskillst.domain.repositories

import com.hamilton.gamesskillst.domain.models.GameSkill

interface GameSkillsRepository {
    fun getGameSkills(): List<GameSkill>
}