package innovappte.mobile.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onNewItemOnTop(listener: (Int) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lm = recyclerView.layoutManager
            if (lm is LinearLayoutManager) {
                val itemPosition = lm.findFirstVisibleItemPosition()
                listener(itemPosition)
            }
        }
    })
}