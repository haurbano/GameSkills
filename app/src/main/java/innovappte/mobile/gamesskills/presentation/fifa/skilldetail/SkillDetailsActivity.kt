package innovappte.mobile.gamesskills.presentation.fifa.skilldetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import kotlinx.android.synthetic.main.activity_skill_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class SkillDetailsActivity : AppCompatActivity() {

    private val viewModel: SkillDetailsVM by viewModel()

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
        setListeners()
        listenViewModel()
    }

    private fun setListeners() {
        imageViewBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        val skill = intent.getSerializableExtra(GAME_SKILL_KEY) as GameSkill
        startVideos(skill)
        setViewValues(skill)
    }

    private fun startVideos(skill: GameSkill) {
        viewModel.startSkillVideo(skill)
        viewModel.startControlVideo(skill)
    }

    private fun startVideo(videoUri: Uri, videoView: VideoView) {
        val videoMediaController = MediaController(this)
        videoView.setVideoURI(videoUri)
        videoMediaController.setMediaPlayer(videoView)
        videoView.setMediaController(videoMediaController)
        videoView.requestFocus()
        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()
    }

    private fun setViewValues(skill: GameSkill) {
        // TODO: get skill from server, just send the ID to this activity
        textViewSkillName.text = skill.name.default
    }

    private fun listenViewModel() {
        viewModel.skillVideoUri.observe(this, Observer { videoUri ->
            startVideo(videoUri, videoViewSkillDetails)
        })

        viewModel.controlVideoUri.observe(this, Observer { videoUri ->
            startVideo(videoUri, videoViewControl)
        })
    }
}
