package hevinxx.iinc.record.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import hevinxx.iinc.record.Record
import java.util.*

@Entity(tableName = "records")
data class RecordRoomDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val habitId: Int,
    val date: Date,
    var achievement: Double,
    var memo: String,
    var recordedAt: Date
// TODO: Image
)

fun RecordRoomDbEntity.toDomainEntity(): Record {
    return Record(
        habitId = this.habitId,
        date = this.date,
        achievement = this.achievement,
        memo = this.memo,
        recordedAt = this.recordedAt
    )
}

fun Record.toRoomDbEntity(): RecordRoomDbEntity {
    return RecordRoomDbEntity(
        habitId = this.habitId,
        date = this.date,
        achievement = this.achievement,
        memo = this.memo,
        recordedAt = this.recordedAt
    )
}