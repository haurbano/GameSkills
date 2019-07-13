package innovappte.mobile.data.repositories

import android.app.DownloadManager
import android.content.Context

import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.data.VideoPathUtils


class GameSkillRepositoryImpl(
        private val firebaseDatabase: FirebaseDatabase,
        private val context: Context
) : GameSkillsRepository {

    override fun getFifaGameSkills(listener: (List<GameSkill>) -> Unit) {
        val skillsReference = firebaseDatabase.reference.child("skills")
        skillsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val gameSkills = dataSnapshot.getValue(GameSkillsResponse::class.java)
                listener(gameSkills?.skills ?: emptyList())
            }

        })
    }

    override fun downloadSkillsVideos(skills: List<GameSkill>){
        skills.forEach { skill ->
            if (!alreadyDownloaded(skill, VideoType.Skill)) downloadVideo(skill, VideoType.Skill)
            if (!alreadyDownloaded(skill, VideoType.Ps4Classic)) downloadVideo(skill, VideoType.Ps4Classic)
            if (!alreadyDownloaded(skill, VideoType.Ps4Alternative)) downloadVideo(skill, VideoType.Ps4Alternative)
        }
    }

    private fun downloadVideo(skill: GameSkill, videoType: VideoType) {
        Log.d("GameSkills Debug", "Downloading new video - ${skill.name.default}")
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
        downloadManager?.enqueue(buildRequest(skill, videoType))
    }

    private fun buildRequest(skill: GameSkill, videoType: VideoType): DownloadManager.Request {
        val uri = Uri.parse(getVideoUrl(skill, videoType))
        val destinationUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, skill, videoType))
        return DownloadManager.Request(uri)
                .setDestinationUri(destinationUri)
    }

    private fun alreadyDownloaded(skill: GameSkill, videoType: VideoType): Boolean {
        val file = VideoPathUtils.getVideoFile(context, skill, videoType)
        return file?.exists() ?: false
    }

    private fun getVideoUrl(skill: GameSkill, videoType: VideoType): String {
        return when (videoType) {
            VideoType.Skill -> skill.skillVideo
            VideoType.Ps4Classic -> skill.ps4ControlClassicVideo
            VideoType.Ps4Alternative -> skill.ps4ControlAlternativeVideo
        }
    }

    enum class VideoType {
        Skill, Ps4Classic, Ps4Alternative
    }

}