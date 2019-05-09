package com.hamilton.gamesskillst.data

import android.app.DownloadManager
import android.content.Context
import com.google.gson.Gson
import com.hamilton.gamesskillst.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import com.hamilton.gamesskillst.domain.repositories.GameSkillsRepository
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.util.Log
import innovappte.mobile.gamesskills.data.VideoPathUtils


class GameSkillRepositoryImpl(
    private val dataFilesManager: DataFilesManager,
    private val context: Context
) : GameSkillsRepository {
    companion object{
        const val GAME_SKILLS_FILE_NAME = "game_skill_example.json"
    }

    override fun getFifaGameSkills(): List<GameSkill> {
        val jsonString = dataFilesManager.getAssetAsString(GAME_SKILLS_FILE_NAME)
        return Gson().fromJson(jsonString, GameSkillsResponse::class.java).skills
    }

    override fun downloadSkillsVideos(skills: List<GameSkill>){
        skills.forEach { skill ->
            if (!alreadyDownloaded(skill)) downloadVideo(skill)
        }
    }

    private fun downloadVideo(skill: GameSkill) {
        Log.d("GameSkills Debug", "Downloading new video - ${skill.name.default}")
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
        downloadManager?.enqueue(buildRequest(skill))
    }

    private fun buildRequest(skill: GameSkill): DownloadManager.Request {
        val uri = Uri.parse(skill.skillVideo)
        val destinationUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, skill))
        return DownloadManager.Request(uri)
                .setDestinationUri(destinationUri)
    }

    private fun alreadyDownloaded(skill: GameSkill): Boolean {
        val file = VideoPathUtils.getVideoFile(context, skill)
        return file?.exists() ?: false
    }

}