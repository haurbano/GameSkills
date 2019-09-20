package innovappte.mobile.gamesskills.presentation.fifa.celebrations

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import im.ene.toro.CacheManager
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.fifa.celebrationdetail.CelebrationDetailActivity
import innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters.FiFaCelebrationAdapter
import kotlinx.android.synthetic.main.activity_celebration_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class CelebrationListActivity : AppCompatActivity() {

    private val viewModel: CelebrationListVM by viewModel()
    private val celebrationsAdapter by lazy { FiFaCelebrationAdapter(
            emptyList(),
            this,
            ActionToViewMapper(),
            VideoPathUtils(this),
            onCelebrationClicked) }

    companion object {
        fun getIntent(context: Context) = Intent(context, CelebrationListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebration_list)
        setupRecyclerView()
        setupLayoutListener()
    }

    private fun setupLayoutListener() {
        imgBackCelebrationList.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        with(recyclerViewCelebrations) {
            adapter = celebrationsAdapter
            layoutManager = LinearLayoutManager(this@CelebrationListActivity)
            cacheManager = CacheManager.DEFAULT
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.prepareCelebrationData()
        listenCelebrationListChanges()
    }

    private fun listenCelebrationListChanges() {
        viewModel.celebrationList.observe(this, Observer { celebrations ->
            if (celebrations != null) celebrationsAdapter.items = celebrations
            celebrationsAdapter.notifyDataSetChanged()
        })
    }

    private val onCelebrationClicked = { celebration: FiFaCelebration ->
        val intent = CelebrationDetailActivity.getIntent(this, celebration.id)
        startActivity(intent)
    }
}
