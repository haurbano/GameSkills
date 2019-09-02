package innovappte.mobile.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import innovappte.mobile.data.mappers.FiFaCelebrationMapper
import innovappte.mobile.domain.models.FiFaCelebration
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject

class FiFaCelebrationsFirebaseDataSource(
        private val fireBaseDatabase: FirebaseFirestore,
        private val fiFaCelebrationMapper: FiFaCelebrationMapper
) {
    fun getFiFaCelebrations(): Single<List<FiFaCelebration>> {
        val single = SingleSubject.create<List<FiFaCelebration>>()

        fireBaseDatabase.collection(FiFaFirebaseCollections.CELEBRATIONS).get()
                .addOnSuccessListener { result ->
                    val mappedResults = fiFaCelebrationMapper(result.documents)
                    single.onSuccess(mappedResults)

                }
                .addOnFailureListener { error -> single.onError(error) }

        return single.hide()
    }
}