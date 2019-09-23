package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroPlayer.EventListener
import im.ene.toro.ToroUtil.visibleAreaOffset
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.exoplayer.MediaSourceBuilder
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.adapters.BaseViewHolder
import innovappte.mobile.gamesskills.presentation.models.GameSkillViewInfo


class SkillItemViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        val actionToViewMapper: ActionToViewMapper,
        val clickListener: (GameSkill) -> Unit,
        private val videoPathUtils: VideoPathUtils,
        val context: Context
        ): BaseViewHolder(inflater.inflate(R.layout.item_fifa_game_skill, parent, false)), ToroPlayer {

    private var currentVideoUri: Uri? = null
    private var helper: ExoPlayerViewHelper? = null
    private val player = itemView.findViewById(R.id.videoViewGameSkill) as PlayerView


    private val nameTextView: TextView = itemView.findViewById(R.id.textViewNameGameSkill)
    private val skillStepsContainer = itemView.findViewById<LinearLayout>(R.id.skillStepsContainer)
    private val videoPreview = itemView.findViewById<ImageView>(R.id.imgVideoPreview)

    override fun bind(item: Any?) {
        val skill = (item as GameSkillViewInfo).gameSkill
        val videoUri = Uri.fromFile(videoPathUtils.getVideoFile(skill, VideoType.Main))
        val videoPreviewUri = videoPathUtils.getVideoPreviewUri(skill)
        videoPreview.setImageURI(videoPreviewUri)
        currentVideoUri = videoUri
        nameTextView.text = formatedName(skill)
        itemView.setOnClickListener { clickListener(skill) }
        skillStepsContainer.removeAllViews()
        val stepsViews = actionToViewMapper(context, skill.actions)
        stepsViews.forEach { skillStepsContainer.addView(it) }
        skillStepsContainer.invalidate()
        player.videoSurfaceView.setOnClickListener { clickListener(skill) }
    }

    private fun formatedName(skill: GameSkill): String {
        val stars = "✭".repeat(skill.skillMoves)
        return "$stars - ${skill.name.default}"
    }

    override fun isPlaying(): Boolean = helper?.isPlaying ?: false

    override fun getPlayerView(): View = player

    override fun pause() {
        helper?.pause()
    }

    override fun wantsToPlay(): Boolean = visibleAreaOffset(this, itemView.parent) >= 0.95

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
            helper!!.addPlayerEventListener(listener)
        }
        helper!!.initialize(container, playbackInfo)
    }

    override fun getPlayerOrder(): Int = adapterPosition

    private val listener by lazy {
        object: EventListener {
            override fun onBuffering() {
            }

            override fun onFirstFrameRendered() {
                videoPreview.visibility = View.INVISIBLE
            }

            override fun onPlaying() {
                videoPreview.visibility = View.INVISIBLE
            }

            override fun onPaused() {
                videoPreview.visibility = View.VISIBLE
            }

            override fun onCompleted() {
                videoPreview.visibility = View.VISIBLE
            }

        }
    }
}