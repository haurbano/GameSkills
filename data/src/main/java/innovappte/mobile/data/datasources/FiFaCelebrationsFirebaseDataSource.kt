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

    fun getFiFaCelebration(celebrationId: String): Single<FiFaCelebration> {
        val single = SingleSubject.create<FiFaCelebration>()
        fireBaseDatabase.collection(FiFaFirebaseCollections.CELEBRATIONS)
                .document(celebrationId).get()
                .addOnSuccessListener { result ->
                    val mappedCelebration = fiFaCelebrationMapper(result)
                    if (mappedCelebration != null) {
                        single.onSuccess(mappedCelebration)
                    } else {
                        single.onError(Throwable("Error parsing single celebration"))
                    }
                }
                .addOnFailureListener { single.onError(it) }
        return single.hide()
    }
}