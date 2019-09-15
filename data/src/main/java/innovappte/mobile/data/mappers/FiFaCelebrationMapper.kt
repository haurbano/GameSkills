package innovappte.mobile.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import innovappte.mobile.domain.models.FiFaCelebration

class FiFaCelebrationMapper {
    operator fun invoke(input: List<DocumentSnapshot>): List<FiFaCelebration> {
        val celebrationList = arrayListOf<FiFaCelebration>()
        input.forEach { document ->
            val celebration = document.toObject(FiFaCelebration::class.java)
            if (celebration != null) {
                celebration.id = document.id
                celebrationList.add(celebration)
            }
        }
        return celebrationList
    }

    operator fun invoke(document: DocumentSnapshot): FiFaCelebration? {
        return document.toObject(FiFaCelebration::class.java)
    }
}