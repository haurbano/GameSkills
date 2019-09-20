package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper

class FifaSkillAdapter(
        var items: List<GameSkill>,
        val context: Context,
        val clickListener: (GameSkill) -> Unit,
        val actionToViewMapper: ActionToViewMapper,
        val videoPathUtils: VideoPathUtils
): RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SkillItemViewHolder(inflater, parent, actionToViewMapper, clickListener, videoPathUtils, context)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val skill = items[position]
        holder.bind(skill)
    }
}