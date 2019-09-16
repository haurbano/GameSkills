package innovappte.mobile.gamesskills.presentation.fifa.celebrationdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import innovappte.mobile.gamesskills.R
import kotlinx.android.synthetic.main.activity_celebration_detail.*
import org.koin.android.ext.android.inject

class CelebrationDetailActivity : AppCompatActivity() {

    companion object {
        const val CELEBRATION_ID = "celebrationId"
        fun getIntent(context: Context, celebrationId: String): Intent {
            return Intent(context, CelebrationDetailActivity::class.java)
                    .apply { putExtra(CELEBRATION_ID, celebrationId) }
        }
    }

    private val viewModel: CelebrationDetailVM by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebration_detail)
        val celebrationId = intent.getStringExtra(CELEBRATION_ID)
        viewModel.prepareData(celebrationId)
    }

    override fun onResume() {
        super.onResume()
        listenViewModel()

    }

    private fun listenViewModel() {
        viewModel.celebration.observe(this, Observer { celebration ->
            txtCelebrationName.text = celebration.name.default
        })
    }
}
