package hevinxx.iinc.habit.data

import hevinxx.iinc.AppDatabase
import hevinxx.iinc.habit.Habit
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class LocalHabitDataSource(val appDatabase: AppDatabase) : HabitDataSource {
    override fun createNewHabit(newHabit: Habit): Completable {
        val newHabitEntity = HabitRoomDbEntity(
            title = newHabit.title,
            color = newHabit.color,
            daysOfWeek = newHabit.daysOfWeek,
            achievementGrade = newHabit.achievementGrade,
            startDate = newHabit.startDate,
            endDate = newHabit.endDate
        )
        return appDatabase.habitDao().insertAll(newHabitEntity)
    }

    override fun getHabitsByDate(date: Date): Single<List<Habit>> {
        return appDatabase.habitDao().getHabitsByDate(date)
            .map {
                it.filter {
                    checkDow(it, date)
                }.map {
                    Habit(
                        title = it.title,
                        color = it.color,
                        daysOfWeek = it.daysOfWeek,
                        achievementGrade = it.achievementGrade,
                        startDate = it.startDate,
                        endDate = it.endDate
                    )
                }
            }
    }

    private fun checkDow(habitRoomDbEntity: HabitRoomDbEntity, date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dow = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> DayOfWeek.SUNDAY
            2 -> DayOfWeek.MONDAY
            3 -> DayOfWeek.TUESDAY
            4 -> DayOfWeek.WEDNESDAY
            5 -> DayOfWeek.THURSDAY
            6 -> DayOfWeek.FRIDAY
            else -> DayOfWeek.SATURDAY
        }
        return habitRoomDbEntity.daysOfWeek.contains(dow)
    }
}