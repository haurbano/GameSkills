package innovappte.mobile.data.repositories

import innovappte.mobile.authentication.Authenticator
import innovappte.mobile.domain.repositories.AuthenticationRepository
import io.reactivex.Completable

class AuthenticationRepositoryImpl(
        private val authenticator: Authenticator
): AuthenticationRepository {
    override fun login(): Completable {
        return authenticator.login()
    }
}