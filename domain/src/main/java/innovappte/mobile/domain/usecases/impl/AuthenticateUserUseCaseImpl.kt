package innovappte.mobile.domain.usecases.impl

import innovappte.mobile.domain.repositories.AuthenticationRepository
import innovappte.mobile.domain.usecases.interfaces.AuthenticateUserUseCase
import io.reactivex.Completable

class AuthenticateUserUseCaseImpl(
        val authenticationRepository: AuthenticationRepository
): AuthenticateUserUseCase {
    override fun invoke(): Completable {
        return authenticationRepository.login()
    }
}