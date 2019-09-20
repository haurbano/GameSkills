package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Any?)
}