package innovappte.mobile.gamesskills.presentation.mappers

import innovappte.mobile.gamesskills.R

//TODO: Move this to other module in order to use it from different presentation modules
// o features modules.
class ButtonMapper {
    operator fun invoke(button: String): List<Int>? {
        return map[button]
    }
    private val map = hashMapOf<String, List<Int>>(
            "button1"   to listOf(R.drawable.ic_button_ps4_button1),
            "r1"        to listOf(R.drawable.ic_button_ps4_r1),
            "r2"        to listOf(R.drawable.ic_button_ps4_r2),
            "l1"        to listOf(R.drawable.ic_button_ps4_l1),
            "l2"        to listOf(R.drawable.ic_button_ps4_l2),
            "r"         to listOf(R.drawable.ic_button_ps4_r),
            "l"         to listOf(R.drawable.ic_button_ps4_l),
            "rUp"       to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_up),
            "rDown"     to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_down),
            "rLeft"     to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_left),
            "rRight"    to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_right),
            "lUp"       to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_up),
            "lDown"     to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_down),
            "lLeft"     to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_left),
            "lRight"    to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_right),
            "lRight"    to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_right)
    )
}