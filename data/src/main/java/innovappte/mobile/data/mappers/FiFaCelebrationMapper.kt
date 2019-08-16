package innovappte.mobile.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import innovappte.mobile.domain.models.FiFaCelebration

class FiFaCelebrationMapper {
    operator fun invoke(input: List<DocumentSnapshot>): List<FiFaCelebration> {
        val celebrationList = arrayListOf<FiFaCelebration>()
        input.forEach { document ->
            val gameSkill = document.toObject(FiFaCelebration::class.java)
            if (gameSkill != null) {
                gameSkill.id = document.id
                celebrationList.add(gameSkill)
            }
        }
        return celebrationList
    }

    operator fun invoke(document: DocumentSnapshot): FiFaCelebration? {
        return document.toObject(FiFaCelebration::class.java)
    }
}