package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.BaseViewHolder

class FiFaCelebrationAdapter(
        var items: List<FiFaCelebration>,
        val context: Context,
        val actionToViewMapper: ActionToViewMapper,
        val videoPathUtils: VideoPathUtils,
        val onCelebrationClicked: (celebration: FiFaCelebration) -> Unit = {}
): RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var currentVideoUri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CelebrationItemViewHolder(
                inflater,
                parent,
                actionToViewMapper,
                videoPathUtils,
                context,
                onCelebrationClicked
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val celebration = items[position]
        holder.bind(celebration)
    }
}