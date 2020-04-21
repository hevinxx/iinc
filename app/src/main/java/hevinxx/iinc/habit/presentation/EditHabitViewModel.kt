package hevinxx.iinc.habit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import hevinxx.iinc.Event
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.habit.data.HabitColor
import hevinxx.iinc.set
import java.util.*

abstract class EditHabitViewModel(
    private val resourceDataSource: ResourceDataSource
) {
    val title = MutableLiveData("")

    val colors = HabitColor.values()
    val colorIndex = MutableLiveData<Int>()

    val daysOfWeek = MutableLiveData(
        mutableListOf(true, true, true, true, true, true, true)
    )

    fun turnOnOrOffDayOfWeek(dayOfWeek: DayOfWeek) {
        val index = dayOfWeek.index
        val preveiousBoolean = daysOfWeek.value?.get(index) ?: false
        daysOfWeek.set(index, !preveiousBoolean)
    }

    protected val _achievementGrade = MutableLiveData(2)
    val achievementGrade: LiveData<String>
        get() = Transformations.map(_achievementGrade) {
            it.toString()
        }

    protected val dateFormat = resourceDataSource.getDateFormat()
    protected val _startDate = MutableLiveData(Date())
    val startDate: LiveData<String>
        get() = Transformations.map(_startDate) {
            dateFormat.format(it)
        }
    protected val _endDate = MutableLiveData(Date())
    val endDate: LiveData<String>
        get() = Transformations.map(_endDate) {
            dateFormat.format(it)
        }

    protected val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    protected val _finishEvent = MutableLiveData<Event<Boolean>>()
    val finishEvent: LiveData<Event<Boolean>>
        get() = _finishEvent

    abstract fun editHabit()

    protected fun getNewHabitInstance(): Habit? {
        return try {
            Habit(
                title = title.value!!,
                color = resourceDataSource.getColor(colors[colorIndex.value!!].colorId),
                daysOfWeek = daysOfWeek.value!!,
                achievementGrade = _achievementGrade.value!!,
                startDate = _startDate.value!!,
                endDate = _endDate.value!!
            )
        } catch (e: NullPointerException) {
            null
        }
    }

    protected fun emitToastMessage(message: String) {
        _toastMessage.value = Event(message)
    }

    protected fun finish() {
        _finishEvent.value = Event(true)
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