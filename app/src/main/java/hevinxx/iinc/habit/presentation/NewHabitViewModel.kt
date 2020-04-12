package hevinxx.iinc.habit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hevinxx.iinc.Event
import hevinxx.iinc.R
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.habit.data.HabitRepository
import hevinxx.iinc.set
import java.util.*

class NewHabitViewModel(
    private val habitRepository: HabitRepository,
    private val resourceDataSource: ResourceDataSource
) {
    val title = MutableLiveData<String>("")

    val color = MutableLiveData<Int>()
    fun setColor(colorCode: Int) {
        color.value = colorCode
    }

    val daysOfWeek = MutableLiveData<MutableList<Boolean>>(
        mutableListOf(true, true, true, true, true, true, true)
    )

    fun turnOnOrOffDayOfWeek(dayOfWeek: DayOfWeek) {
        val index = dayOfWeek.index
        val preveiousBoolean = daysOfWeek.value?.get(index) ?: false
        daysOfWeek.set(index, !preveiousBoolean)
    }

    val achievementGrade = MutableLiveData<Int>(2)

    val startDate = MutableLiveData<Date>()
    val endDate = MutableLiveData<Date>()

    val needToAlarm = MutableLiveData<Boolean>()

    private val _incompleteFieldError = MutableLiveData<Event<String>>()
    val incompleteFieldError: LiveData<Event<String>>
        get() = _incompleteFieldError

    fun createNewHabit() {
        val newHabit = getNewHabitInstance()
        newHabit?.let { habitRepository.postNewHabit(it) }
            ?: run { emitIncompleteFieldError() }
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
            null
        }
    }

    private fun emitIncompleteFieldError() {
        val errorMessage = resourceDataSource.getString(R.string.incomplete_field_error)
        _incompleteFieldError.value = Event(errorMessage)
    }
}

enum class DayOfWeek(val index: Int) {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
}