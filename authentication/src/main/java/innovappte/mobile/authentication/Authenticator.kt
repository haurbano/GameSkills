package innovappte.mobile.authentication

import io.reactivex.Completable

interface Authenticator {
    fun login(): Completable
}