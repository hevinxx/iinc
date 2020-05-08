package hevinxx.iinc.habit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import hevinxx.iinc.util.Event
import hevinxx.iinc.R
import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.util.Dow
import hevinxx.iinc.habit.data.HabitColor
import java.util.*

abstract class EditHabitViewModel(
    private val resourceDataSource: ResourceDataSource
) {
    val title = MutableLiveData("")

    val colors = HabitColor.values()
    val colorIndex = MutableLiveData<Int>()

    val dows = Dow.values()
    val dowsSelection = mutableSetOf<Dow>()

    fun turnOnOrOffDow(dow: Dow) {
        if (dowsSelection.contains(dow)) {
            dowsSelection.remove(dow)
        } else {
            dowsSelection.add(dow)
        }
    }

    val grades = resourceDataSource.getStringArray(R.array.grades)
    var achievementGrade = 2
        set(position) {
            mapGradePositionToGrade(position)
        }

    private fun mapGradePositionToGrade(position: Int): Int {
        return position + 2
    }

    protected val _startDate = MutableLiveData(Date())
    protected val dateFormat = resourceDataSource.getDateFormat()
    val startDate: LiveData<String>
        get() = Transformations.map(_startDate) {
            dateFormat.format(it)
        }

    fun setStartDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        _startDate.value = calendar.time
    }

    protected val _endDate = MutableLiveData<Date>(null)
    val endDate: LiveData<String>
        get() = Transformations.map(_endDate) {
            if (it == null) resourceDataSource.getString(R.string.no_choice)
            else dateFormat.format(it)
        }

    fun setEndDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        _endDate.value = calendar.time
    }

    fun setEndDate(x: Nothing?) {
        _endDate.value = null
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
                dows = dowsSelection,
                achievementGrade = achievementGrade,
                startDate = _startDate.value!!,
                endDate = _endDate.value
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