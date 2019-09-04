package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import innovappte.mobile.common.L
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.data.VideoPathUtils


class FifaSkillAdapter(
        var items: List<GameSkill>,
        val context: Context,
        val clickListener: (GameSkill) -> Unit
): RecyclerView.Adapter<FifaSkillAdapter.ViewHolder>() {

    lateinit var videoUri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_game_skill, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nameTextView.text = items[position].name.default
        setupVideo(position, VideoType.Main)
        holder.itemView.setOnClickListener { clickListener(items[position]) }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), ToroPlayer {
        var videoHelper: ExoPlayerViewHelper? = null
        val nameTextView = itemView.findViewById<TextView>(R.id.textViewNameGameSkill)
        val videoViewSkill = itemView.findViewById<PlayerView>(R.id.videoViewGameSkill)

        override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
            if(videoHelper == null) {
                videoHelper = ExoPlayerViewHelper(this, videoUri)
            }

            videoHelper?.initialize(container, playbackInfo)
        }


        override fun isPlaying() = videoHelper?.isPlaying ?: false

        override fun getPlayerView() = videoViewSkill

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

    private fun setupVideo(position: Int, videoType: VideoType) {
        val gameSkill = items[position]
        videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, gameSkill, videoType))
    }


}