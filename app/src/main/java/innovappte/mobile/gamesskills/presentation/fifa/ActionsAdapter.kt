package innovappte.mobile.gamesskills.presentation.fifa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.domain.models.Action
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.mappers.ActionMapper
import innovappte.mobile.gamesskills.presentation.mappers.ButtonMapper

class ActionsAdapter(
        var data: List<Action>,
        private val actionMapper: ActionMapper,
        private val buttonMapper: ButtonMapper
) : RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageViewAction = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_action_step, parent, false)
        return ViewHolder(imageViewAction)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val action = data[position]
        val actionResource = actionMapper(action.action)
        holder.imgAction.setImageResource(actionResource?.first() ?: 0)

        val button = action.button
        if (button != null) {
            val buttonResource = buttonMapper(button)
            holder.imgButton.setImageResource(buttonResource?.first() ?: 0)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgAction: ImageView = view.findViewById(R.id.imageViewAction)
        val imgButton: ImageView = view.findViewById(R.id.imageViewButtonAction)
    }
}