package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class RemoteHabitDataSource : HabitDataSource {
    override fun createNewHabit(newHabit: Habit): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHabitsByDate(date: Date): Single<List<Habit>> {
        TODO("Not yet implemented")
    }
}