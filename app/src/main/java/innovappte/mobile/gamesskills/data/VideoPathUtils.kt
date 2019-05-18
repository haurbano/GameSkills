package innovappte.mobile.gamesskills.data

import android.content.Context
import com.hamilton.gamesskillst.data.GameSkillRepositoryImpl
import com.hamilton.gamesskillst.domain.models.GameSkill
import java.io.File

object VideoPathUtils {
    private const val VIDEOS_FOLDER_NAME = "GameSkillsVideos"

    fun getVideoFile(context: Context, skill: GameSkill, videoType: GameSkillRepositoryImpl.VideoType): File? {
        return when (videoType) {
            GameSkillRepositoryImpl.VideoType.Skill -> getVideoFile(context, skill)
            GameSkillRepositoryImpl.VideoType.Ps4Alternative -> getPs4AlternativeVideo(context, skill)
            GameSkillRepositoryImpl.VideoType.Ps4Classic -> getPs4ClassicVideo(context, skill)
        }
    }

    private fun getVideoFile(context: Context, skill: GameSkill): File? {
        val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
        return File(folder, skill.name.default)
    }

    private fun getPs4ClassicVideo(context: Context, skill: GameSkill): File? {
        val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
        return File(folder, "ps4_classic_${skill.name.default}")
    }

    private fun getPs4AlternativeVideo(context: Context, skill: GameSkill): File? {
        val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
        return File(folder, "ps4_alternative_${skill.name.default}")
    }

}