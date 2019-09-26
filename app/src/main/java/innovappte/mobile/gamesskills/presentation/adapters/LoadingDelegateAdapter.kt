package innovappte.mobile.gamesskills.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.gamesskills.R

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoadingViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ViewType) {
        // Do nothing...
    }

    class LoadingViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder(
            inflater.inflate(R.layout.item_loading, parent, false)
    ) {
        override fun bind(item: Any?) {
            // Do nothing..
        }
    }
}