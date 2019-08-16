package innovappte.mobile.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import innovappte.mobile.domain.models.Action

class ActionMapper {
    operator fun invoke(input: List<DocumentSnapshot>): List<Action> {
        val actionList = arrayListOf<Action>()
        input.forEach { document ->
            val gameSkill = document.toObject(Action::class.java)
            if (gameSkill != null) {
                actionList.add(gameSkill)
            }
        }
        return actionList
    }
}