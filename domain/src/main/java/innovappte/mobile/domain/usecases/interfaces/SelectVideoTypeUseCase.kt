package innovappte.mobile.domain.usecases.interfaces

import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode

interface SelectVideoTypeUseCase {
    operator fun invoke(controlMode: FifaControlMode, consoleType: ConsoleType): VideoType
}