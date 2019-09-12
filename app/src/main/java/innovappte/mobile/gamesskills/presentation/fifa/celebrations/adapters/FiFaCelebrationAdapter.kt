package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.net.Uri
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.exoplayer.MediaSourceBuilder
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import innovappte.mobile.common.L
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper

class FiFaCelebrationAdapter(
        var items: List<FiFaCelebration>,
        val context: Context,
        val actionToViewMapper: ActionToViewMapper
): RecyclerView.Adapter<FiFaCelebrationAdapter.ViewHolder>() {

    lateinit var videoUri: Uri

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview), ToroPlayer {
        var videoHelper: ExoPlayerViewHelper? = null
        val title = itemview.findViewById<TextView>(R.id.textViewItemCelebrationTitle)
        val video = itemview.findViewById<PlayerView>(R.id.playerView)
        val imgSteps = itemview.findViewById<LinearLayout>(R.id.recyclerCelebrationSteps)

        override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
            if(videoHelper == null) {
                val videoConfig = Config.Builder()
                        .setMediaSourceBuilder(MediaSourceBuilder.LOOPING)
                        .build()
                videoHelper = ExoPlayerViewHelper(this, videoUri, null, videoConfig)
            }

            videoHelper?.initialize(container, playbackInfo)
        }


        override fun isPlaying(): Boolean {
            return  videoHelper?.isPlaying ?: false
        }

        override fun getPlayerView() = video

        override fun pause() {
            videoHelper?.pause()
        }

        override fun wantsToPlay(): Boolean {
            return ToroUtil.visibleAreaOffset(this, itemView.parent) >= 0.85
        }

        override fun play() {
            videoHelper?.play()
        }

        override fun getCurrentPlaybackInfo(): PlaybackInfo {
            return videoHelper?.latestPlaybackInfo ?: PlaybackInfo()
        }

        override fun release() {
            if (videoHelper != null) {
                videoHelper?.release()
                videoHelper = null
            }
        }

        override fun getPlayerOrder() = adapterPosition
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
        videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, celebration, videoType))
    }
}