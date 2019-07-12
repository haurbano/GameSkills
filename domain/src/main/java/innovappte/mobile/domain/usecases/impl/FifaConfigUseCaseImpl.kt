package innovappte.mobile.domain.usecases.impl

import innovappte.mobile.domain.repositories.FiFaSharedPreferenceRepository
import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase

class FifaConfigUseCaseImpl(
    private val fifaSharedPreferenceRepository: FiFaSharedPreferenceRepository
) : FifaConfigUseCase {
    override fun getControlMode(): FifaControlMode {
        return FifaControlMode.valueOf(fifaSharedPreferenceRepository.getFifaControlMode())
    }

    override fun getConsoleType(): ConsoleType {
        return ConsoleType.valueOf(fifaSharedPreferenceRepository.getConsoleType())
    }

    override fun setControlMode(controlMode: FifaControlMode) {
        fifaSharedPreferenceRepository.setFifaControlMode(controlMode)
    }

    override fun setConsoleType(consoleType: ConsoleType) {
        fifaSharedPreferenceRepository.setConsoleType(consoleType)
    }
}