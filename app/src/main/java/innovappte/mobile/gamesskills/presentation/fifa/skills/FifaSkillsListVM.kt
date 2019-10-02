package innovappte.mobile.gamesskills.presentation.fifa.skills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.common.L
import innovappte.mobile.common.TaskResult
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.presentation.adapters.ViewType
import innovappte.mobile.gamesskills.presentation.adapters.ViewTypeValues
import innovappte.mobile.gamesskills.presentation.models.GameSkillViewInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {

    private val completeSkillList = arrayListOf<ViewType>()
    
    val gameSkills: MutableLiveData<List<ViewType>> =
            MutableLiveData<List<ViewType>>().apply { value = arrayListOf() }
    val endListLoader: MutableLiveData<TaskResult> = MutableLiveData<TaskResult>().apply { value = TaskResult.Progress }


    private val disposables = CompositeDisposable()

    fun prepareGameSkillsData() {
        val disposable = gameSkillsUseCase.getGameSkills()
                .subscribeOn(Schedulers.io())
                .flatMapObservable { skills -> gameSkillsUseCase.downloadMainSkillVideos(skills) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { addLoaderAtTheEndOfList(emptyList()) }
                .doOnTerminate { endListLoader.value = TaskResult.Success(Unit) }
                .subscribe({ partialSkills->
                    addLoaderAtTheEndOfList(partialSkills)
                }, { error -> L.e(error) })

        disposables.add(disposable)
    }

    private fun addLoaderAtTheEndOfList(partialSkills: List<GameSkill>) {
        completeSkillList.apply { addAll(partialSkills.map { GameSkillViewInfo(it) }) }

        val justGameSkills = completeSkillList.filter { filterByGameSkills(it) }
        val newListPlusLoader = ArrayList(justGameSkills).apply { add(endListLoaderView) }
        gameSkills.value = newListPlusLoader
    }

    private val endListLoaderView = object : ViewType {
        override fun getViewType(): Int {
            return ViewTypeValues.LOADER
        }
    }

    fun searchBy(skillName: String?) {
        if (skillName.isNullOrBlank()) {
            gameSkills.value = completeSkillList.filter { filterByGameSkills(it) }
            return
        }
        val filteredList = completeSkillList
                .filter { filterByGameSkills(it) }
                .filter { skillViewInfo -> filterByName(skillViewInfo, skillName) }
        gameSkills.value = filteredList
    }

    private fun filterByGameSkills(it: ViewType) =
            it.getViewType() == ViewTypeValues.GAME_SKILL

    private fun filterByName(skillViewInfo: ViewType, skillName: String): Boolean {
        val skill = skillViewInfo as GameSkillViewInfo
        return skill.gameSkill.name.default.contains(skillName, true) ||
                skill.gameSkill.name.es.contains(skillName, true)
    }
}