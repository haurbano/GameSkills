package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.FifaSharedPreferenceDataSource
import innovappte.mobile.domain.repositories.FiFaSharedPreferenceRepository
import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode

class FiFaSharedPreferenceRepositoryImpl(
        private val fifaSharedPreferenceDataSource: FifaSharedPreferenceDataSource
) : FiFaSharedPreferenceRepository {
    override fun setFifaControlMode(controlMode: FifaControlMode) {
        fifaSharedPreferenceDataSource.setFifaControlMode(controlMode)
    }

    override fun setConsoleType(consoleType: ConsoleType) {
        fifaSharedPreferenceDataSource.setConsoleType(consoleType)
    }

    override fun getFifaControlMode(): String {
        return fifaSharedPreferenceDataSource.getFifaControlMode()
    }

    override fun getConsoleType(): String {
        return fifaSharedPreferenceDataSource.getConsoleType()
    }
}