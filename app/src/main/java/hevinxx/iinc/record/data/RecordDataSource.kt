package hevinxx.iinc.record.data

import hevinxx.iinc.record.Record
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface RecordDataSource {
    fun saveRecord(record: Record): Completable
    fun getRecordsBetween(from: Date, until: Date): Single<List<Record>>
    fun getRecordsOf(habitIds: IntArray, from: Date, until: Date): Single<List<Record>>
}