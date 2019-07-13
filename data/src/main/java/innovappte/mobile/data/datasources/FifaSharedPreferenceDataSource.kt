package innovappte.mobile.data.datasources

import android.content.Context
import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode

class FifaSharedPreferenceDataSource(context: Context) {

    companion object {
        const val FIFA_PREFERENCE_NAME = "fifa_preference"
        const val CONTROL_MODE_KEY = "control_mode"
        const val CONSOLE_TYPE_KEY = "console_type"
    }

    private val preference by lazy { context.getSharedPreferences(FIFA_PREFERENCE_NAME,Context.MODE_PRIVATE) }

    fun setFifaControlMode(controlMode: FifaControlMode) {
        preference.edit().putString(CONTROL_MODE_KEY, controlMode.name).apply()
    }

    fun setConsoleType(consoleType: ConsoleType) {
        preference.edit().putString(CONSOLE_TYPE_KEY, consoleType.name).apply()
    }

    fun getFifaControlMode() = preference.getString(CONTROL_MODE_KEY, FifaControlMode.CLASSIC.name)

    fun getConsoleType() = preference.getString(CONSOLE_TYPE_KEY, ConsoleType.PS4.name)
}