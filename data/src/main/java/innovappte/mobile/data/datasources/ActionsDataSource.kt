package innovappte.mobile.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import innovappte.mobile.data.datasources.FiFaFirebaseCollections.ACTIONS
import innovappte.mobile.data.mappers.ActionMapper
import innovappte.mobile.domain.models.Action
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

class ActionsDataSource(
        private val fireBaseDatabase: FirebaseFirestore,
        private val actionMapper: ActionMapper
) {
    fun getActions(collection: String, documentId: String): Single<List<Action>> {
        val subject = SingleSubject.create<List<Action>>()
        fireBaseDatabase.collection(collection).document(documentId).collection(ACTIONS).get()
                .addOnSuccessListener { subject.onSuccess(actionMapper(it.documents)) }
                .addOnFailureListener { subject.onError(it) }
        return subject.hide()
    }
}