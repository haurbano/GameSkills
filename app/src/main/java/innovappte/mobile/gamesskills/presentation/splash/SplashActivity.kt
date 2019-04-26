package innovappte.mobile.gamesskills.presentation.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
    }

    override fun onResume() {
        super.onResume()
        this.startActivity(FifaSkillListActivity.getIntent(this))
    }
}
