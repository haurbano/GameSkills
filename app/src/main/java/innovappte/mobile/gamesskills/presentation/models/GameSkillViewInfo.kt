package innovappte.mobile.gamesskills.presentation.models

import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.presentation.adapters.ViewType
import innovappte.mobile.gamesskills.presentation.adapters.ViewTypeValues

class GameSkillViewInfo(val gameSkill: GameSkill): ViewType {
    override fun getViewType(): Int {
        return ViewTypeValues.GAME_SKILL
    }
}