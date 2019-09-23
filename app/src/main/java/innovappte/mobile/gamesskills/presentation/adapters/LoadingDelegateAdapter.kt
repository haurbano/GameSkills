package innovappte.mobile.gamesskills.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.gamesskills.R

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoadingViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, item: ViewType) {
        // Do nothing
    }

    class LoadingViewHolder(inflater: LayoutInflater): BaseViewHolder(
            inflater.inflate(R.layout.item_loading, null)
    ) {
        override fun bind(item: Any?) {

        }
    }
}