package innovappte.mobile.gamesskills.presentation.mappers

import innovappte.mobile.gamesskills.R


class ActionMapper {
    operator fun invoke(action: String): List<Int>? {
        return map[action]
    }

    private val map = hashMapOf (
            "hold" to listOf(R.drawable.ic_action_ps4_hold_en),
            "next" to listOf(R.drawable.ic_action_ps4_next)
    )
}