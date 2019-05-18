package innovappte.mobile.gamesskills.presentation.fifa.home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import innovappte.mobile.gamesskills.R
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillListActivity
import kotlinx.android.synthetic.main.activity_fifa_home.*

class FiFaHomeActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FiFaHomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifa_home)
        setListeners()
    }

    private fun setListeners() {
        textViewPlays.setOnClickListener { openSkillList() }
        imgBgPlays.setOnClickListener { openSkillList() }
    }

    private fun openSkillList() {
        startActivity(FifaSkillListActivity.getIntent(this))
    }
}
