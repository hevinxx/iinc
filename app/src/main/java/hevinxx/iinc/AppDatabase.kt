package hevinxx.iinc

import android.content.Context
import androidx.room.*
import hevinxx.iinc.util.Dow
import hevinxx.iinc.habit.data.HabitDao
import hevinxx.iinc.habit.data.HabitRoomDbEntity
import hevinxx.iinc.record.data.RecordDao
import hevinxx.iinc.record.data.RecordRoomDbEntity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

@Database(entities = [HabitRoomDbEntity::class, RecordRoomDbEntity::class], version = 1)
@TypeConverters(DateConverters::class, DowConverters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "IincDb")
                    .build()
            }
        }
    }
    abstract fun habitDao(): HabitDao
    abstract fun recordDao(): RecordDao
}

class DateConverters {
    @TypeConverter
    fun timeStringToDate(timeString: String?): Date? {
        return timeString?.let { SimpleDateFormat("yyyy-MM-dd").parse(timeString) }
    }

    @TypeConverter
    fun dateToTimeString(date: Date?): String? {
        return date?.let { SimpleDateFormat("yyyy-MM-dd").format(date) }
    }
}

class DowConverters {
    @TypeConverter
    fun setOfDowsToBits(setOfDows: Set<Dow>): Int {
        return setOfDows.map {
            2.0.pow(it.getDatabaseIndex()).toInt()
        }.reduce { acc, it ->
            acc or it
        }
    }

    @TypeConverter
    fun bitsToSetOfDows(bits: Int): Set<Dow> {
        return Dow.values().filter {
            2.0.pow(it.getDatabaseIndex()).toInt() and bits > 0
        }.toSet()
    }
}