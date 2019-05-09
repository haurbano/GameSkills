package innovappte.mobile.gamesskills.data

import android.content.Context
import com.hamilton.gamesskillst.domain.models.GameSkill
import java.io.File

class VideoPathUtils {
    companion object {
        private const val VIDEOS_FOLDER_NAME = "GameSkillsVideos"

        fun getVideoFile(context: Context, skill: GameSkill): File? {
            val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
            return File(folder, skill.name.default)
        }
    }
}