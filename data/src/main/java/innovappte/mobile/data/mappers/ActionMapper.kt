package innovappte.mobile.data.mappers

import com.google.firebase.firestore.DocumentSnapshot
import innovappte.mobile.common.L
import innovappte.mobile.domain.models.Action
import java.lang.RuntimeException

class ActionMapper {
    var currentId = ""
    operator fun invoke(input: List<DocumentSnapshot>): List<Action> {
        val actionList = arrayListOf<Action>()
        input.forEach { document ->
            try {
                val action = document.toObject(Action::class.java)
                if (action != null) {
                    actionList.add(action)
                }
            } catch (error: RuntimeException) {
                L.e(error)
            }
        }
        return actionList
    }
}