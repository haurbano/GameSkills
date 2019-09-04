package innovappte.mobile.gamesskills.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseFiFaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Any?)
}