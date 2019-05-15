package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.data.VideoPathUtils


class FifaSkillAdapter(var items: List<GameSkill>, val context: Context, val clickListener: (GameSkill) -> Unit): RecyclerView.Adapter<FifaSkillAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_game_skill, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.textViewNameGameSkill)
        val videoView = holder.itemView.findViewById<VideoView>(R.id.videoViewGameSkill)
        nameTextView.text = items[position].name.default
        setupVideo(videoView, position)
        holder.itemView.setOnClickListener { clickListener(items[position]) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private fun setupVideo(videoView: VideoView, position: Int) {
        val gameSkill = items[position]
        val videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, gameSkill))
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()
    }
}