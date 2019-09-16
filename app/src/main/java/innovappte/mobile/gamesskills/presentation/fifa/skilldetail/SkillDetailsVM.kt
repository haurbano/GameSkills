package innovappte.mobile.gamesskills.presentation.fifa.skilldetail

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.domain.usecases.interfaces.SelectVideoTypeUseCase
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase

class SkillDetailsVM(
    private val videoPathUtils: VideoPathUtils,
    private val fifaConfigUseCase: FifaConfigUseCase,
    private val selectVideoTypeUseCase: SelectVideoTypeUseCase
): ViewModel() {

    val skillVideoUri: MutableLiveData<Uri> = MutableLiveData()
    val controlVideoUri: MutableLiveData<Uri> = MutableLiveData()

    fun startSkillVideo(skill: GameSkill) {
        val videoUri = Uri.fromFile(videoPathUtils.getVideoFile(skill, VideoType.Main))
        skillVideoUri.value = videoUri
    }

    fun startControlVideo(skill: GameSkill) {
        val controlMode = fifaConfigUseCase.getControlMode()
        val consoleType = fifaConfigUseCase.getConsoleType()
        val videoType = selectVideoTypeUseCase(controlMode, consoleType)
        val videoUri = Uri.fromFile(videoPathUtils.getVideoFile(skill, videoType))
        controlVideoUri.value = videoUri
    }
}