package innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters

import android.content.Context
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.net.Uri
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val title = itemview.findViewById<TextView>(R.id.textViewItemCelebrationTitle)
        val video = itemview.findViewById<TextureView>(R.id.textureViewVideoCelebrationItem)
        val controlVideo = itemview.findViewById<TextureView>(R.id.textureViewControlCelebrationVideo)
        val imgSteps = itemview.findViewById<RecyclerView>(R.id.recyclerCelebrationSteps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fifa_celebration, null, false)
        return ViewHolder(view).apply {
            imgSteps.layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val celebration = items[position]
        holder.title.text = celebration.name.default
        setupVideo(holder.video, position, VideoType.Main)
        holder.imgSteps.adapter = actionAdapter.apply { data = celebration.actions }

    }

    private fun setupVideo(videoView: TextureView, position: Int, videoType: VideoType) {
        val gameSkill = items[position]
        videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, gameSkill, videoType))
        videoView.surfaceTextureListener = surfaceListener
    }

    private val surfaceListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture?, p1: Int, p2: Int) {
            L.i("onSurfaceTextureSizeChanged")
        }

        override fun onSurfaceTextureUpdated(p0: SurfaceTexture?) {
            L.i("onSurfaceTextureUpdated")
        }

        override fun onSurfaceTextureDestroyed(p0: SurfaceTexture?): Boolean {
            L.i("onSurfaceTextureDestroyed")
            return true
        }

        override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture?, p1: Int, p2: Int) {
            L.i("onSurfaceTextureAvailable")
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