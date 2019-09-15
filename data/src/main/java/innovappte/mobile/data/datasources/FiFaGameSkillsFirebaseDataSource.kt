package innovappte.mobile.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import innovappte.mobile.data.mappers.GameSkillsMapper
import innovappte.mobile.domain.models.GameSkill
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

class FiFaGameSkillsFirebaseDataSource(
        private val fireBaseDatabase: FirebaseFirestore,
        private val mapper: GameSkillsMapper
) {
    fun getFiFaSkills(): Single<List<GameSkill>> {
        val single = SingleSubject.create<List<GameSkill>>()

        fireBaseDatabase.collection(FiFaFirebaseCollections.SKILLS).get()
                .addOnSuccessListener { result ->
                    single.onSuccess(mapper(result.documents))
                }
                .addOnFailureListener { error -> single.onError(error) }
        
        return single
    }
}