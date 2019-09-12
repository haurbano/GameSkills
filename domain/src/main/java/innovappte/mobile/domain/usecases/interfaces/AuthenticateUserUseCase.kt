package innovappte.mobile.domain.usecases.interfaces

import io.reactivex.Completable

interface AuthenticateUserUseCase {
    operator fun invoke(): Completable
}