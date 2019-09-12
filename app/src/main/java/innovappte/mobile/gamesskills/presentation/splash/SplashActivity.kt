package innovappte.mobile.gamesskills.presentation.splash

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import innovappte.mobile.common.TaskResult
import innovappte.mobile.gamesskills.presentation.fifa.home.FiFaHomeActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashVM by inject()

    override fun onResume() {
        super.onResume()
        viewModel.loginUser().observe(this, Observer { result ->
            if (result is TaskResult.Success<*>) {
                goToMainActivity()
            } else {
                showErrorInLogin()
            }
        })
    }

    private fun goToMainActivity() {
        this.startActivity(FiFaHomeActivity.getIntent(this))
    }

    private fun showErrorInLogin() {
        Toast.makeText(this, "Error Login", Toast.LENGTH_LONG).show()
        finish()
    }
}
