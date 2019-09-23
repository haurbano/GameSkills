package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.adapters.BaseViewHolder
import innovappte.mobile.gamesskills.presentation.adapters.ViewType
import innovappte.mobile.gamesskills.presentation.adapters.ViewTypeDelegateAdapter

class SkillDelegateAdapter(
        private val actionToViewMapper: ActionToViewMapper,
        private val clickListener: (GameSkill) -> Unit,
        private val videoPathUtils: VideoPathUtils,
        val context: Context
): ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SkillItemViewHolder(inflater, parent, actionToViewMapper, clickListener, videoPathUtils, context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ViewType) {
        holder.bind(item)
    }
}