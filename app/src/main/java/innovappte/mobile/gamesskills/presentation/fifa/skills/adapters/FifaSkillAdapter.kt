package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.exoplayer.Config
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.exoplayer.MediaSourceBuilder
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.videoplayer.ToroPlayerSetup


class FifaSkillAdapter(
        var items: List<GameSkill>,
        val context: Context,
        val clickListener: (GameSkill) -> Unit,
        val actionToViewMapper: ActionToViewMapper,
        val videoPathUtils: VideoPathUtils
): RecyclerView.Adapter<FifaSkillAdapter.ViewHolder>() {

    lateinit var currentVideoUri: Uri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_game_skill, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setupVideo(position, VideoType.Main)
        val skill = items[position]
        holder.nameTextView.text = items[position].name.default
        holder.itemView.setOnClickListener { clickListener(skill) }
        holder.skillStepsContainer.removeAllViews()
        val stepsViews = actionToViewMapper(context, skill.actions)
        stepsViews.forEach { holder.skillStepsContainer.addView(it) }
        holder.skillStepsContainer.invalidate()
        holder.videoViewSkill.invalidate()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), ToroPlayerSetup {

        private val singleVideoHelper by lazy {
            val videoConfig = Config.Builder()
                    .setMediaSourceBuilder(MediaSourceBuilder.LOOPING)
                    .build()
            ExoPlayerViewHelper(this, getVideoUri(), null, videoConfig)
        }

        val nameTextView: TextView = itemView.findViewById<TextView>(R.id.textViewNameGameSkill)
        val videoViewSkill = itemView.findViewById<PlayerView>(R.id.videoViewGameSkill)
        val skillStepsContainer = itemView.findViewById<LinearLayout>(R.id.skillStepsContainer)

        override fun getVideoUri() = currentVideoUri

        override fun getVideoView(): PlayerView = videoViewSkill

        override fun getViewParent(): ViewParent = itemView.parent

        override fun getVideoHelper() = singleVideoHelper
    }

    private fun setupVideo(position: Int, videoType: VideoType) {
        val gameSkill = items[position]
        currentVideoUri = Uri.fromFile(videoPathUtils.getVideoFile(gameSkill, videoType))
        Log.i("--haur", "skill video: $currentVideoUri")
    }


}