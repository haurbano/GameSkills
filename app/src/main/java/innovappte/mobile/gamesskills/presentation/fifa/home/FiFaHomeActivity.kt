package innovappte.mobile.gamesskills.presentation.fifa.home

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import innovappte.mobile.gamesskills.R
import innovappte.mobile.domain.models.ConsoleType
import innovappte.mobile.gamesskills.domain.models.FifaControlMode
import innovappte.mobile.gamesskills.presentation.fifa.celebrations.CelebrationListActivity
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillListActivity
import innovappte.mobile.gamesskills.presentation.utils.FiFaDialog
import kotlinx.android.synthetic.main.activity_fifa_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class FiFaHomeActivity : AppCompatActivity() {

    private val viewModel: FifaHomeVM by viewModel()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FiFaHomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_fifa_home)
        setListeners()
        setVMListeners()
    }

    private fun setVMListeners() {
        listenConsoleType()
        listenControlMode()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun listenControlMode() {
        viewModel.controlMode.observe(this, Observer { controlMode ->
            when (controlMode){
                FifaControlMode.CLASSIC -> setClassicControlMode()
                FifaControlMode.ALTERNATIVE -> setAlternativeControlMode()
            }
        })
    }

    private fun listenConsoleType() {
        viewModel.consoleType.observe(this, Observer { consoleType ->
            when (consoleType) {
                ConsoleType.PS4 -> setPs4AsConsoleType()
                ConsoleType.XBOX -> setXboxAsConsoleType()
            }
        })
    }

    private fun setListeners() {
        textViewPlays.setOnClickListener { openSkillList() }
        imgBgPlays.setOnClickListener { openSkillList() }

        textViewCelebrations.setOnClickListener { openCelebrations() }
        imgBgCelebrations.setOnClickListener { openCelebrations() }

        textViewPlayers.setOnClickListener { showComingFeatureDialog() }
        imgBgPlayers.setOnClickListener { showComingFeatureDialog() }

        textViewAdvise.setOnClickListener { showComingFeatureDialog() }
        imgBgAdvise.setOnClickListener { showComingFeatureDialog() }

        imgBtnPs4.setOnClickListener { changeConsoleType(ConsoleType.PS4) }
        imgbtnXbox.setOnClickListener { changeConsoleType(ConsoleType.XBOX) }

        textViewModeClasic.setOnClickListener { changeControlMode(FifaControlMode.CLASSIC) }
        textViewModeAlternative.setOnClickListener { changeControlMode(FifaControlMode.ALTERNATIVE) }
    }

    private fun openSkillList() {
        startActivity(FifaSkillListActivity.getIntent(this))
    }

    private fun openCelebrations() {
        startActivity(CelebrationListActivity.getIntent(this))
    }

    private fun showComingFeatureDialog() {
        FiFaDialog.Builder()
                .Build()
                .show(this)
    }

    private fun changeConsoleType(consoleType: ConsoleType) {
        when (consoleType) {
            ConsoleType.PS4 -> viewModel.setConsoleType(ConsoleType.PS4)
            ConsoleType.XBOX -> viewModel.setConsoleType(ConsoleType.XBOX)
        }
    }

    private fun changeControlMode(controlMode: FifaControlMode) {
        when (controlMode) {
            FifaControlMode.CLASSIC -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewModel.setControlMode(FifaControlMode.CLASSIC)
            }
            FifaControlMode.ALTERNATIVE -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewModel.setControlMode(FifaControlMode.ALTERNATIVE)
            }
        }
    }

    private fun setPs4AsConsoleType() {
        imgBtnPs4.setImageResource(R.drawable.btn_ps4_on)
        imgbtnXbox.setImageResource(R.drawable.btn_xbox_on) // TODO: change this to btn_xbox_off
    }

    private fun setXboxAsConsoleType() {
        imgBtnPs4.setImageResource(R.drawable.btn_ps4_off)
        imgbtnXbox.setImageResource(R.drawable.btn_xbox_on)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setClassicControlMode() {
        textViewModeClasic.setTextAppearance(R.style.TextViewFifaHomeModeSelected)
        textViewModeAlternative.setTextAppearance(R.style.TextViewFifaHomeModeUnselected)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAlternativeControlMode() {
        textViewModeAlternative.setTextAppearance(R.style.TextViewFifaHomeModeSelected)
        textViewModeClasic.setTextAppearance(R.style.TextViewFifaHomeModeUnselected)
    }

}
