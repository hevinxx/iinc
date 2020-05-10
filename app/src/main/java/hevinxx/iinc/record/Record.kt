package hevinxx.iinc.record

import java.util.*

data class Record(
    val id: Int = 0,
    val habitId: Int,
    val date: Date,
    var achievement: Double,
    var memo: String,
    var recordedAt: Date
// TODO: Image
)