package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import im.ene.toro.CacheManager
import im.ene.toro.widget.Container
import innovappte.mobile.common.TaskResult
import innovappte.mobile.common.onNewItemOnTop
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.actionmapper.ActionToViewMapper
import innovappte.mobile.gamesskills.presentation.adapters.ViewTypeValues
import innovappte.mobile.gamesskills.presentation.fifa.skilldetail.SkillDetailsActivity
import innovappte.mobile.gamesskills.presentation.fifa.skills.adapters.FifaSkillAdapter
import innovappte.mobile.gamesskills.presentation.models.GameSkillViewInfo
import kotlinx.android.synthetic.main.activity_fifa_skill_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FifaSkillListActivity : AppCompatActivity() {

    private val viewModel: FifaSkillsListVM by viewModel()
    private val onItemListListener = { skill: GameSkill -> goToDetails(skill) }
    private val gameSkillsAdapter = FifaSkillAdapter(
            ArrayList(1),
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
    }

    private fun setListeners() {
        imgBackSkillLsit.setOnClickListener { finish() }
        editSearchSkill.addTextChangedListener(queryTextListener)
        ratingBarSkills.onRatingBarChangeListener = onStarsChange
    }

    private val onStarsChange = RatingBar.OnRatingBarChangeListener { _, stars, changeByUser ->
        if (changeByUser){
            moveListToSkillsWithStart(stars)
        }
    }

    private val queryTextListener = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.searchBy(query?.toString())
        }
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
            onNewItemOnTop(this@FifaSkillListActivity::calculateStarsOnRatingBar)
        }
    }

    private fun setUpGameSkills() {
        viewModel.gameSkills.observe(this, Observer { gameSkills ->
            if (gameSkills != null) gameSkillsAdapter.updateItems(gameSkills)
            gameSkillsAdapter.notifyDataSetChanged()
        })

        viewModel.endListLoader.observe(this, Observer { result ->
            if (result is TaskResult.Success<*>) {
                gameSkillsAdapter.removeEndListLoader()
            }
        })
    }

    private fun goToDetails(skill: GameSkill) {
        val intent = SkillDetailsActivity.getIntent(this, skill)
        startActivity(intent)
    }

    private fun moveListToSkillsWithStart(stars: Float) {
        val item = gameSkillsAdapter.items.firstOrNull {
            it.getViewType() == ViewTypeValues.GAME_SKILL &&
                    (it as GameSkillViewInfo).gameSkill.skillMoves == stars.toInt()
        }
        item?.let {
            val position = gameSkillsAdapter.items.indexOf(it)
            (recyclerViewSkills.layoutManager as? LinearLayoutManager)?.
                    scrollToPositionWithOffset(position, 0)
        }
    }

    private fun calculateStarsOnRatingBar(itemPosition: Int) {
        val skill = gameSkillsAdapter.items[itemPosition]
        if (skill is GameSkillViewInfo) {
            val stars = skill.gameSkill.skillMoves
            ratingBarSkills.rating = stars.toFloat()
        }
    }
}
