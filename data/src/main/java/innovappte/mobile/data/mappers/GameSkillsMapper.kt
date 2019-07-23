package innovappte.mobile.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import innovappte.mobile.domain.models.GameSkill

class GameSkillsMapper {
    operator fun invoke(input: List<DocumentSnapshot>): List<GameSkill> {
        val gameSkills = arrayListOf<GameSkill>()
        input.forEach { document ->
            val gameSkill = document.toObject(GameSkill::class.java)
            if (gameSkill != null) {
                gameSkills.add(gameSkill)
            }
        }
        return gameSkills
    }
}