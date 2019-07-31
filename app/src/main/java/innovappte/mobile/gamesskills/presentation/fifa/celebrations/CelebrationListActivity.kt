package innovappte.mobile.gamesskills.presentation.fifa.celebrations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import innovappte.mobile.gamesskills.R
import org.koin.android.viewmodel.ext.android.viewModel

class CelebrationListActivity : AppCompatActivity() {

    private val viewModel: CelebrationListVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_celebration_list)
        viewModel.prepareCelebrationData()
    }


}
