package innovappte.mobile.gamesskills.domain.usecases.impl

import innovappte.mobile.gamesskills.data.FifaSharedPreferenceHandler
import innovappte.mobile.gamesskills.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase

class FifaConfigUseCaseImpl(
    private val fifaSharedPreferenceHandler: FifaSharedPreferenceHandler
) : FifaConfigUseCase {
    override fun getControlMode(): FifaControlMode {
        return FifaControlMode.valueOf(fifaSharedPreferenceHandler.getFifaControlMode())
    }

    override fun getConsoleType(): ConsoleType {
        return ConsoleType.valueOf(fifaSharedPreferenceHandler.getConsoleType())
    }

    override fun setControlMode(controlMode: FifaControlMode) {
        fifaSharedPreferenceHandler.setFifaControlMode(controlMode)
    }

    override fun setConsoleType(consoleType: ConsoleType) {
        fifaSharedPreferenceHandler.setConsoleType(consoleType)
    }
}