package innovappte.mobile.domain.repositories

import io.reactivex.Completable

interface AuthenticationRepository {
    fun login(): Completable
}