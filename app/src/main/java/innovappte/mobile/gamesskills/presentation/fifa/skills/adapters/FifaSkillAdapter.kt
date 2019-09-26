package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.view.*
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.adapters.*

class FifaSkillAdapter(
        var items: ArrayList<ViewType>,
        val context: Context,
        clickListener: (GameSkill) -> Unit,
        actionToViewMapper: ActionToViewMapper,
        videoPathUtils: VideoPathUtils
): RecyclerView.Adapter<BaseViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(ViewTypeValues.LOADER, LoadingDelegateAdapter())
        delegateAdapters.put(ViewTypeValues.GAME_SKILL, SkillDelegateAdapter(
                actionToViewMapper,
                clickListener,
                videoPathUtils,
                context
            )
        )
    }

    private val endListLoaderView = object : ViewType {
        override fun getViewType(): Int {
            return ViewTypeValues.LOADER
        }
    }

    fun updateItems(data: List<ViewType>) {
        items.clear()
        items.addAll(data)
        items.add(endListLoaderView)
    }

    fun removeEndListLoader() {
        items.removeAt(itemCount - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        delegateAdapters.get(getItemViewType(position))?.onBindViewHolder(holder, item)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }
}