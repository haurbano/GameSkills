package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.FifaSkillAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FifaSkillListActivity : AppCompatActivity() {

    private val mViewModel: FifaSkillsListVM by viewModel()
    private val gameSkillsAdapter: FifaSkillAdapter by lazy { FifaSkillAdapter(emptyList(), this) }

    lateinit var recyclerViewSkills: RecyclerView

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FifaSkillListActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifa_skill_list)
        recyclerViewSkills = findViewById(R.id.recyclerViewSkills)
        setUpRecyclerView()
        setUpGameSkills()
    }

    private fun setUpRecyclerView() {
        with(recyclerViewSkills) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FifaSkillListActivity)
            adapter = gameSkillsAdapter
        }
    }

    private fun setUpGameSkills() {
        mViewModel.gameSkills.observe(this, Observer { gameSkills ->
            if (gameSkills != null) gameSkillsAdapter.items = gameSkills
            gameSkillsAdapter.notifyDataSetChanged()
        })
    }
}
