package hevinxx.iinc.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.habit.data.HabitRepository
import hevinxx.iinc.record.Record
import hevinxx.iinc.record.data.RecordRepository
import hevinxx.iinc.util.log.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

class TodayViewModel(
    private val habitRepository: HabitRepository,
    private val recordRepository: RecordRepository,
    private val logger: Logger
) {
    private val TAG = "TodayViewModel"

    private val _todayHabitAndRecords = MutableLiveData<List<Pair<Habit, Record?>>>()
    val todayHabitAndRecords: LiveData<List<Pair<Habit, Record?>>>
        get() = _todayHabitAndRecords

    fun initTodayHabitsAndRecords() {
        getTodayHabits().zipWith<List<Record>, List<Pair<Habit, Record?>>>(getTodayRecords(),
            BiFunction { habits, records ->
                habits.map { habit ->
                    Pair(habit, records.find { it?.habitId == habit.id })
                }
            }).subscribeBy({
                logger.w(TAG, "getTodayHabitsAndRecords error: $it")
            }, {
                _todayHabitAndRecords.value = it
            })
    }

    private fun getTodayHabits(): Single<List<Habit>> {
        val todayDate = Date()
        return habitRepository.getHabitsByDate(todayDate)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                logger.w(TAG, "getHabitsByDate error: $it, date was ${todayDate}")
            }
    }

    private fun getTodayRecords(): Single<List<Record>> {
        val todayDate = Date()
        return recordRepository.getRecordsBetween(todayDate, todayDate)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                logger.w(TAG, "getRecordOf error: $it, date was $todayDate")
            }
    }
}