package innovappte.mobile.gamesskills.presentation.fifa.skills.adapters

import android.content.Context
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import innovappte.mobile.data.repositories.GameSkillRepositoryImpl
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.data.VideoPathUtils


class FifaSkillAdapter(var items: List<GameSkill>, val context: Context, val clickListener: (GameSkill) -> Unit): RecyclerView.Adapter<FifaSkillAdapter.ViewHolder>() {

    lateinit var videoUri: Uri
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_game_skill, null, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.textViewNameGameSkill)
        val videoViewSkill = holder.itemView.findViewById<TextureView>(R.id.videoViewGameSkill)
        nameTextView.text = items[position].name.default
        setupVideo(videoViewSkill, position, GameSkillRepositoryImpl.VideoType.Skill)
        holder.itemView.setOnClickListener { clickListener(items[position]) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private fun setupVideo(videoView: TextureView, position: Int, videoType: GameSkillRepositoryImpl.VideoType) {
        val gameSkill = items[position]
        videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, gameSkill, videoType))
        videoView.surfaceTextureListener = surfaceListener
    }

    private val surfaceListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
            return true
        }

        override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture?, p1: Int, p2: Int) {
            val surface = Surface(surfaceTexture)
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(videoUri.path)
            mediaPlayer.setSurface(surface)
            mediaPlayer.isLooping = true
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
        }
    }
}