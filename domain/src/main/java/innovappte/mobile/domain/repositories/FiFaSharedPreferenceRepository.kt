package innovappte.mobile.domain.repositories

import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode

interface FiFaSharedPreferenceRepository {
    fun setFifaControlMode(controlMode: FifaControlMode)

    fun setConsoleType(consoleType: ConsoleType)

    fun getFifaControlMode(): String

    fun getConsoleType(): String
}