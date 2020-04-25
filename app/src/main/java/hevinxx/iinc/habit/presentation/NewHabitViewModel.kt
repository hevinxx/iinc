package hevinxx.iinc.habit.presentation

import hevinxx.iinc.R
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.data.HabitRepository
import io.reactivex.rxkotlin.subscribeBy

class NewHabitViewModel(
    private val habitRepository: HabitRepository,
    private val resourceDataSource: ResourceDataSource
) : EditHabitViewModel(resourceDataSource) {

    override fun editHabit() {
        createNewHabit()
    }

    private fun createNewHabit() {
        val newHabit = getNewHabitInstance()
        newHabit?.let {
            habitRepository.postNewHabit(it)
                .subscribeBy({
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