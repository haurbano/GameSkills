package innovappte.mobile.domain.usecases.impl

import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.domain.models.ConsoleType.PS4
import innovappte.mobile.domain.models.ConsoleType.XBOX
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.domain.usecases.interfaces.SelectVideoTypeUseCase
import innovappte.mobile.gamesskills.domain.models.FifaControlMode
import innovappte.mobile.gamesskills.domain.models.FifaControlMode.CLASSIC
import innovappte.mobile.gamesskills.domain.models.FifaControlMode.ALTERNATIVE

class SelectVideoTypeUseCaseImpl: SelectVideoTypeUseCase {

    override operator fun invoke(controlMode: FifaControlMode, consoleType: ConsoleType): VideoType {
        return when {
            controlMode == CLASSIC && consoleType == PS4 -> VideoType.Ps4Classic
            controlMode == CLASSIC && consoleType == XBOX -> VideoType.XboxClassic
            controlMode == ALTERNATIVE && consoleType == PS4 -> VideoType.Ps4Alternative
            controlMode == ALTERNATIVE && consoleType == XBOX -> VideoType.XboxAlterative
            else -> VideoType.Ps4Classic
        }
    }
}