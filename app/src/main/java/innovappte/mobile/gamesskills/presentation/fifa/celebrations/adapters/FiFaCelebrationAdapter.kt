package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import innovappte.mobile.common.L
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.fifa.ActionsAdapter
import innovappte.mobile.gamesskills.presentation.mappers.ActionMapper
import innovappte.mobile.gamesskills.presentation.mappers.ButtonMapper

class FiFaCelebrationAdapter(
        var items: List<FiFaCelebration>,
        val context: Context
): RecyclerView.Adapter<FiFaCelebrationAdapter.ViewHolder>() {

    val actionAdapter by lazy { ActionsAdapter(emptyList(), ActionMapper(), ButtonMapper()) }

    lateinit var videoUri: Uri

    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview), ToroPlayer {
        var videoHelper: ExoPlayerViewHelper? = null
        val title = itemview.findViewById<TextView>(R.id.textViewItemCelebrationTitle)
        val video = itemview.findViewById<PlayerView>(R.id.playerView)
//        val controlVideo = itemview.findViewById<TextureView>(R.id.textureViewControlCelebrationVideo)
        val imgSteps = itemview.findViewById<RecyclerView>(R.id.recyclerCelebrationSteps)

        override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
            L.i("Initialize video")
            if(videoHelper == null) {
                videoHelper = ExoPlayerViewHelper(this, videoUri)
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
        return ViewHolder(view).apply {
            imgSteps.layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val celebration = items[position]
        holder.title.text = celebration.name.default
        setupVideo(position, VideoType.Main)
        holder.imgSteps.adapter = actionAdapter.apply { data = celebration.actions }
    }

    private fun setupVideo(position: Int, videoType: VideoType) {
        val celebration = items[position]
        videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, celebration, videoType))
    }
}