package innovappte.mobile.gamesskills.presentation.fifa.celebrations

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import im.ene.toro.CacheManager
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.fifa.celebrations.adapters.FiFaCelebrationAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_celebration_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class CelebrationListActivity : AppCompatActivity() {

    private val viewModel: CelebrationListVM by viewModel()
    private val celebrationsAdapter by lazy { FiFaCelebrationAdapter(emptyList(), this) }

    companion object {
        fun getIntent(context: Context) = Intent(context, CelebrationListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebration_list)
        setupRecylerView()
        setupLayoutListener()
    }

    private fun setupLayoutListener() {
        imgBackCelebrationList.setOnClickListener { finish() }
    }

    private fun setupRecylerView() {
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


}
