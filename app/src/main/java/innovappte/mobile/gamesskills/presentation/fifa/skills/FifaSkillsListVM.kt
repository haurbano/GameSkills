package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.common.L
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.presentation.adapters.ViewType
import innovappte.mobile.gamesskills.presentation.adapters.ViewTypeValues
import innovappte.mobile.gamesskills.presentation.models.GameSkillViewInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {
    val gameSkills: MutableLiveData<List<ViewType>> =
            MutableLiveData<List<ViewType>>().apply { value = arrayListOf() }
    val endListLoader: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = View.VISIBLE }


    private val disposables = CompositeDisposable()

    fun prepareGameSkillsData() {
        val temporal = arrayListOf<ViewType>()
        val disposable = gameSkillsUseCase.getGameSkills()
                .subscribeOn(Schedulers.io())
                .flatMapObservable { skills -> gameSkillsUseCase.downloadMainSkillVideos(skills) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete { Log.i("--haur", "on Complete download videos") }
                .subscribe({ partialSkills->
                    temporal.addAll(partialSkills.map { GameSkillViewInfo(it) })
                    gameSkills.value = temporal
                }, { error -> L.e(error) }, { endListLoader.value = View.GONE})

        disposables.add(disposable)
    }
}