package innovappte.mobile.common

import android.util.Log

object L {
    private const val TAG_I = "Info: "
    private const val TAG_E = "Error: "

    fun i(message: String) {
        Log.i(TAG_I, message)
    }

    fun e(error: Throwable) {
        Log.e(TAG_E, error.message)
    }
}