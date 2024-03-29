package innovappte.mobile.gamesskills.actionmapper

import innovappte.mobile.gamesskills.R

//TODO: Move this to other module in order to use it from different presentation modules
// o features modules.
class ButtonMapper {

    operator fun invoke(button: String?): List<Int>? {
        if (button == null) return emptyList()
        return map[button]
    }
    private val map = hashMapOf(
            "button1"   to listOf(R.drawable.ic_button_ps4_button1),
            "button2"   to listOf(R.drawable.ic_button_ps4_button2),
            "button3"   to listOf(R.drawable.ic_button_ps4_button3),
            "button4"   to listOf(R.drawable.ic_button_ps4_button4),
            "r1"        to listOf(R.drawable.ic_button_ps4_r1),
            "r2"        to listOf(R.drawable.ic_button_ps4_r2),
            "l1"        to listOf(R.drawable.ic_button_ps4_l1),
            "l2"        to listOf(R.drawable.ic_button_ps4_l2),
            "r"         to listOf(R.drawable.ic_button_ps4_r),
            "r3"        to listOf(R.drawable.r3),
            "l"         to listOf(R.drawable.ic_button_ps4_l),
            "l3"        to listOf(R.drawable.l3),
            "rUp"       to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_up),
            "rDown"     to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_down),
            "rLeft"     to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_left),
            "rRight"    to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_right),
            "lUp"       to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_up),
            "lDown"     to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_down),
            "lLeft"     to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_left),
            "lRight"    to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_right),
            "lRight"    to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_right),
            "rLeftUp"   to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_left_up),
            "rLeftDown" to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_left_down),
            "rRightUp"  to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_right_up),
            "rRightDown" to listOf(R.drawable.ic_button_ps4_r, R.drawable.ic_button_ps4_left_down),

            "lLeftUp"   to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_left_up),
            "lLeftDown" to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_left_down),
            "lRightUp"  to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_right_up),
            "lRightDown" to listOf(R.drawable.ic_button_ps4_l, R.drawable.ic_button_ps4_left_down),

            "hold"      to listOf(R.drawable.ic_action_ps4_hold_en),
            "next"      to listOf(R.drawable.ic_action_ps4_next),
            "press"     to listOf(),
            "tap"       to listOf(), //build tap text
            "plus"      to listOf(R.drawable.ic_action_plus),
            "or"        to listOf(R.drawable.ic_or_slash),
            "flick"     to listOf() //build flick text
    )
}