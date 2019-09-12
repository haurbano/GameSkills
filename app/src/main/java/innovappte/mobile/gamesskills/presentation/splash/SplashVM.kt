package innovappte.mobile.gamesskills.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.common.TaskResult
import innovappte.mobile.domain.usecases.interfaces.AuthenticateUserUseCase
import io.reactivex.disposables.CompositeDisposable

class SplashVM(val authUseCase: AuthenticateUserUseCase): ViewModel() {
    private val disposables = CompositeDisposable()

    private val loginStatus: MutableLiveData<TaskResult> = MutableLiveData<TaskResult>()

    fun loginUser(): MutableLiveData<TaskResult> {
        val disposable = authUseCase().subscribe({
            loginStatus.value = TaskResult.Success(Unit)
        },{
            loginStatus.value = TaskResult.Failure(it)
        })

        disposables.add(disposable)
        return loginStatus
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        disposables.clear()
    }
}