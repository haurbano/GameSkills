package innovappte.mobile.gamesskills.domain.usecases.interfaces

import innovappte.mobile.gamesskills.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode

interface FifaConfigUseCase {
    fun setControlMode(controlMode: FifaControlMode)
    fun setConsoleType(consoleType: ConsoleType)
    fun getControlMode(): FifaControlMode
    fun getConsoleType(): ConsoleType
}