package innovappte.mobile.gamesskills.presentation.fifa.skilldetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.R
import innovappte.mobile.data.VideoPathUtils
import kotlinx.android.synthetic.main.activity_skill_details.*

class SkillDetailsActivity : AppCompatActivity() {

    lateinit var skill: GameSkill
    companion object {
        private const val GAME_SKILL_KEY = "gameSkillKey"
        fun getIntent(context: Context, skill: GameSkill): Intent {
            return Intent(context, SkillDetailsActivity::class.java).apply {
                putExtra(GAME_SKILL_KEY, skill)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill_details)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        skill = intent.getSerializableExtra(GAME_SKILL_KEY) as GameSkill
        setListeners()
    }

    private fun setListeners() {
        imageViewBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        startVideos(skill)
        setViewValues()
    }

    private fun startVideos(skill: GameSkill) {
        startVideo(skill, videoViewSkillDetails, VideoType.Main)
        startVideo(skill, videoViewControl, VideoType.Ps4Classic)
    }

    private fun startVideo(skill: GameSkill, videoView: VideoView, videoType: VideoType) {

        val videoMediaController = MediaController(this)
        val videoUri = Uri.fromFile(VideoPathUtils.getVideoFile(this, skill, videoType))
        videoView.setVideoURI(videoUri)
        videoMediaController.setMediaPlayer(videoView)
        videoView.setMediaController(videoMediaController)
        videoView.requestFocus()
        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()
    }

    private fun setViewValues() {
        textViewSkillName.text = skill.name.default
    }
}
