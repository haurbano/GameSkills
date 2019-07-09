package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.common.ViewStatus
import innovappte.mobile.gamesskills.presentation.fifa.skilldetail.SkillDetailsActivity
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.FifaSkillAdapter
import kotlinx.android.synthetic.main.activity_fifa_skill_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FifaSkillListActivity : AppCompatActivity() {

    private val viewModel: FifaSkillsListVM by viewModel()
    private val onItemListListener = { skill: GameSkill -> goToDetails(skill) }
    private val gameSkillsAdapter: FifaSkillAdapter by lazy { FifaSkillAdapter(emptyList(), this, onItemListListener) }

    private lateinit var recyclerViewSkills: RecyclerView

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
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FifaSkillListActivity)
            adapter = gameSkillsAdapter
        }
    }

    private fun setUpGameSkills() {
        viewModel.gameSkills.observe(this, Observer { gameSkills ->
            if (gameSkills != null) gameSkillsAdapter.items = gameSkills
            gameSkillsAdapter.notifyDataSetChanged()
        })
    }

    private fun listenLoaderStatus() {
        viewModel.loaderViewStatus.observe(this, Observer { viewStatus ->
            when (viewStatus) {
                ViewStatus.HIDDEN -> hideLoader()
                ViewStatus.SHOWED -> showLoader()
            }
        })
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
