package innovappte.mobile.data.datasources

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

class FiFaGameSkillsFirebaseDataSource(
        private val fireBaseDatabase: FirebaseFirestore
) {
    fun getFiFaSkills(): Single<List<DocumentSnapshot>> {
        val single = SingleSubject.create<List<DocumentSnapshot>>()

        fireBaseDatabase.collection("skills").get()
                .addOnSuccessListener { result -> single.onSuccess(result.documents) }
                .addOnFailureListener {error -> single.onError(error) }
        
        return single
    }
}