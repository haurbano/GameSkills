package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import im.ene.toro.CacheManager
import im.ene.toro.widget.Container
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.fifa.skilldetail.SkillDetailsActivity
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.FifaSkillAdapter
import kotlinx.android.synthetic.main.activity_fifa_skill_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FifaSkillListActivity : AppCompatActivity() {

    private val viewModel: FifaSkillsListVM by viewModel()
    private val onItemListListener = { skill: GameSkill -> goToDetails(skill) }
    private val gameSkillsAdapter = FifaSkillAdapter(
            emptyList(),
            this,
            onItemListListener,
            ActionToViewMapper(),
            VideoPathUtils(this))

    private lateinit var recyclerViewSkills: Container

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FifaSkillListActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifa_skill_list)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        recyclerViewSkills = findViewById(R.id.recyclerViewSkills)
        setListeners()
        setUpRecyclerView()
        setUpGameSkills()
        listenLoaderStatus()
    }

    private fun setListeners() {
        imgBackSkillLsit.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.prepareGameSkillsData()
    }

    private fun setUpRecyclerView() {
        with(recyclerViewSkills) {
            adapter = gameSkillsAdapter
            layoutManager = LinearLayoutManager(this@FifaSkillListActivity)
            cacheManager = CacheManager.DEFAULT
        }
    }

    private fun setUpGameSkills() {
        viewModel.gameSkills.observe(this, Observer { gameSkills ->
            if (gameSkills != null) gameSkillsAdapter.items = gameSkills
            gameSkillsAdapter.notifyDataSetChanged()
        })
    }

    private fun listenLoaderStatus() {

    }

    private fun hideLoader() {

    }

    private fun showLoader() {

    }

    private fun goToDetails(skill: GameSkill) {
        val intent = SkillDetailsActivity.getIntent(this, skill)
        startActivity(intent)
    }
}
