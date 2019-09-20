package innovappte.mobile.gamesskills.actionmapper

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import innovappte.mobile.domain.models.Action

class ActionToViewMapper {
    operator fun invoke(context: Context, actions: List<Action>): List<View> {
        val resources = getActionsImages(actions)
        return createViews(context, resources)
    }

    private fun getActionsImages( actions: List<Action>): List<Int> {
        val buttonMapper = ButtonMapper()
        val resourceList = arrayListOf<Int>()
        actions.forEach { action ->
            val actions = buttonMapper(action.action)
            val buttons = buttonMapper(action.button)
            if (actions != null) resourceList.addAll(actions)
            if (buttons != null) resourceList.addAll(buttons)
        }
        return  resourceList
    }

    private fun createViews(context: Context, imagesResources: List<Int>): List<View> {
        val views = arrayListOf<View>()
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100)
        imagesResources.forEach { image ->
            val imageView = ImageView(context).apply {
                setImageResource(image)
                setLayoutParams(layoutParams)
            }
            views.add(imageView)
        }
        return views
    }
}