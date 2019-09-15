package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.net.Uri
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.exoplayer.MediaSourceBuilder
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.videoplayer.ToroPlayerSetup

class FiFaCelebrationAdapter(
        var items: List<FiFaCelebration>,
        val context: Context,
        val actionToViewMapper: ActionToViewMapper
): RecyclerView.Adapter<FiFaCelebrationAdapter.ViewHolder>() {

    lateinit var currentVideoUri: Uri

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), ToroPlayerSetup {

        private val singleVideoHelper by lazy {
            val videoConfig = Config.Builder()
                    .setMediaSourceBuilder(MediaSourceBuilder.LOOPING)
                    .build()
            ExoPlayerViewHelper(this, getVideoUri(), null, videoConfig)
        }

        val title = itemView.findViewById<TextView>(R.id.textViewItemCelebrationTitle)
        val currentVideoView = itemView.findViewById<PlayerView>(R.id.playerView)
        val imgSteps = itemView.findViewById<LinearLayout>(R.id.recyclerCelebrationSteps)

        override fun getVideoUri() = currentVideoUri

        override fun getVideoView() = currentVideoView

        override fun getViewParent() = itemView.parent

        override fun getVideoHelper() = singleVideoHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_celebration, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val celebration = items[position]
        holder.title.text = celebration.name.default
        setupVideo(position, VideoType.Main)
        val views = actionToViewMapper(context, celebration.actions)
        holder.imgSteps.removeAllViews()
        views.forEach { holder.imgSteps.addView(it) }
        holder.imgSteps.invalidate()
    }

    private fun setupVideo(position: Int, videoType: VideoType) {
        val celebration = items[position]
        currentVideoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, celebration, videoType))
    }
}