package hevinxx.iinc.habit.presentation

import hevinxx.iinc.R
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.data.HabitRepository
import hevinxx.iinc.util.log.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class NewHabitViewModel(
    private val habitRepository: HabitRepository,
    private val resourceDataSource: ResourceDataSource,
    private val logger: Logger
) : EditHabitViewModel(resourceDataSource) {
    private val TAG = "NewHabitViewModel"

    override fun editHabit() {
        createNewHabit()
    }

    private fun createNewHabit() {
        val newHabit = getNewHabitInstance()
        newHabit?.let {
            habitRepository.createNewHabit(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy({
                    logger.w(TAG, "getNewHabitInstance error: $it")
                    val postFailErrorMessage = resourceDataSource.getString(R.string.post_fail_error)
                    emitToastMessage(postFailErrorMessage)
                }, {
                    finish()
                })
        } ?: run {
            val incompleteFieldErrorMessage =
                resourceDataSource.getString(R.string.incomplete_field_error)
            emitToastMessage(incompleteFieldErrorMessage)
        }
    }
}