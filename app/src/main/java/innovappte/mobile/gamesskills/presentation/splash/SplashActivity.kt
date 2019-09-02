package innovappte.mobile.gamesskills.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import innovappte.mobile.gamesskills.presentation.fifa.home.FiFaHomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Remove this, not necessary.
        Thread.sleep(1000)
    }

    override fun onResume() {
        super.onResume()
        this.startActivity(FiFaHomeActivity.getIntent(this))
    }
}
