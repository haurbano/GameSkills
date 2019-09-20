package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.exoplayer.MediaSourceBuilder
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.BaseViewHolder

class CelebrationItemViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        val actionToViewMapper: ActionToViewMapper,
        private val videoPathUtils: VideoPathUtils,
        val context: Context,
        val onCelebrationClicked: (FiFaCelebration) -> Unit
        ): BaseViewHolder(inflater.inflate(R.layout.item_fifa_celebration, parent, false)), ToroPlayer {

    private var currentVideoUri: Uri? = null
    private var helper: ExoPlayerViewHelper? = null
    private val player = itemView.findViewById(R.id.playerView) as PlayerView

    private val title = itemView.findViewById<TextView>(R.id.textViewItemCelebrationTitle)
    private val imgSteps = itemView.findViewById<LinearLayout>(R.id.recyclerCelebrationSteps)

    override fun isPlaying(): Boolean = helper?.isPlaying ?: false

    override fun getPlayerView(): View = player

    override fun pause() {
        helper?.pause()
    }

    override fun wantsToPlay(): Boolean = ToroUtil.visibleAreaOffset(this, itemView.parent) >= 0.65

    override fun play() {
        helper!!.play()
    }

    override fun getCurrentPlaybackInfo(): PlaybackInfo = helper?.latestPlaybackInfo ?: PlaybackInfo()

    override fun release() {
        helper?.release()
        helper = null
    }

    override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
        if (helper == null) {
            val config = Config.Builder()
                    .setMediaSourceBuilder(MediaSourceBuilder.LOOPING)
                    .build()
            helper = ExoPlayerViewHelper(this, currentVideoUri!!, null, config)
        }
        helper!!.initialize(container, playbackInfo)
    }

    override fun getPlayerOrder(): Int = adapterPosition

    override fun bind(item: Any?) {
        val celebration = item as FiFaCelebration
        currentVideoUri = Uri.fromFile(videoPathUtils.getVideoFile(celebration, VideoType.Main))
        title.text = celebration.name.default
        val views = actionToViewMapper(context, celebration.actions)
        imgSteps.removeAllViews()
        views.forEach { imgSteps.addView(it) }
        imgSteps.invalidate()
        itemView.setOnClickListener { onCelebrationClicked(celebration) }
    }

}