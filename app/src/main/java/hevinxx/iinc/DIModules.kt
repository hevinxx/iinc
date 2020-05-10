package hevinxx.iinc

import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.ResourceDataSourceImpl
import hevinxx.iinc.habit.data.HabitDataSource
import hevinxx.iinc.habit.data.HabitRepository
import hevinxx.iinc.habit.data.LocalHabitDataSource
import hevinxx.iinc.habit.data.RemoteHabitDataSource
import hevinxx.iinc.habit.presentation.NewHabitViewModel
import hevinxx.iinc.record.data.LocalRecordDataSource
import hevinxx.iinc.record.data.RecordDataSource
import hevinxx.iinc.record.data.RecordRepository
import hevinxx.iinc.record.data.RemoteRecordDataSource
import hevinxx.iinc.today.TodayViewModel
import hevinxx.iinc.util.log.Logcat
import hevinxx.iinc.util.log.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val module = module {
    single { AppDatabase.getInstance(androidContext()) }
    single<ResourceDataSource> { ResourceDataSourceImpl(get()) }

    single<Logger> { Logcat() }

    factory { TodayViewModel(get(), get(), get()) }
    single<RecordDataSource>(named("local")) { LocalRecordDataSource(get()) }
    single<RecordDataSource>(named("remote")) { RemoteRecordDataSource() }
    single {
        RecordRepository(
            get(named("local")),
            get(named("remote"))
        )
    }

    factory { NewHabitViewModel(get(), get(), get()) }
    single<HabitDataSource>(named("local")) { LocalHabitDataSource(get()) }
    single<HabitDataSource>(named("remote")) { RemoteHabitDataSource() }
    single {
        HabitRepository(
            get(named("local")),
            get(named("remote"))
        )
    }
}