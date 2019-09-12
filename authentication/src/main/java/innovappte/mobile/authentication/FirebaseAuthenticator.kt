package innovappte.mobile.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

class FirebaseAuthenticator : Authenticator {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(): Completable {
        val subject = CompletableSubject.create()
        val currentUser = auth.currentUser
        if (isUserLoggedIn(currentUser)){
            return subject.apply { onComplete() }
        } else {
            auth.signInAnonymously().addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    subject.onComplete()
                } else {
                    subject.onError(Throwable(task.exception?.cause))
                }
            }
        }
        return subject.hide()
    }

    private fun isUserLoggedIn(user: FirebaseUser?) = user != null
}