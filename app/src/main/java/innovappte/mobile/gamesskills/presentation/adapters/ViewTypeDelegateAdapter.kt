package innovappte.mobile.gamesskills.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    fun onBindViewHolder(holder: BaseViewHolder, item: ViewType)
}