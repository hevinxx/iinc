package hevinxx.iinc.habit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hevinxx.iinc.Event
import hevinxx.iinc.R
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.habit.data.HabitRepository
import java.util.*

class NewHabitViewModel(
    private val habitRepository: HabitRepository,
    private val resourceDataSource: ResourceDataSource
) {
    val title = MutableLiveData<String>("")
    val color = MutableLiveData<Int>()
    val daysOfWeek = MutableLiveData<MutableSet<Int>>()
    val achievementGrade = MutableLiveData<Int>()
    val startDate = MutableLiveData<Date>()
    val endDate = MutableLiveData<Date>()
    val needToAlarm = MutableLiveData<Boolean>()

    private val _notFilledEnoughError = MutableLiveData<Event<String>>()
    val notFilledEnoughError: LiveData<Event<String>>
        get() = _notFilledEnoughError

    fun createNewHabit() {
        val newHabit = getNewHabitInstance()
        if (newHabit != null) habitRepository.postNewHabit(newHabit)
    }

    private fun getNewHabitInstance(): Habit? {
        return try {
            Habit(
                title = title.value!!,
                color = color.value!!,
                daysOfWeek = daysOfWeek.value!!,
                achievementGrade = achievementGrade.value!!,
                startDate = startDate.value!!,
                endDate = endDate.value!!,
                needToAlarm = needToAlarm.value!!
            )
        } catch (e: NullPointerException) {
            val errorMessage = resourceDataSource.getString(R.string.not_fill_enough_error)
            _notFilledEnoughError.value = Event(errorMessage)
            null
        }
    }
}