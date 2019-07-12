package innovappte.mobile.gamesskills.presentation.fifa.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase

class FifaHomeVM(
        private val fifaConfigUseCase: FifaConfigUseCase
) : ViewModel() {

    var controlMode = MutableLiveData<FifaControlMode>().apply { value = fifaConfigUseCase.getControlMode() }
    var consoleType = MutableLiveData<ConsoleType>().apply { value = fifaConfigUseCase.getConsoleType() }

    fun setConsoleType(consoleType: ConsoleType) {
        this.consoleType.value = consoleType
        fifaConfigUseCase.setConsoleType(consoleType)
    }

    fun setControlMode(controlMode: FifaControlMode) {
        this.controlMode.value =  controlMode
        fifaConfigUseCase.setControlMode(controlMode)
    }
}