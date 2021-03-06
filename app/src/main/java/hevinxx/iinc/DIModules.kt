package hevinxx.iinc

import hevinxx.iinc.ResourceDataSource
import hevinxx.iinc.ResourceDataSourceImpl
import hevinxx.iinc.habit.data.HabitDataSource
import hevinxx.iinc.habit.data.HabitRepository
import hevinxx.iinc.habit.data.LocalHabitDataSource
import hevinxx.iinc.habit.data.RemoteHabitDataSource
import hevinxx.iinc.habit.presentation.NewHabitViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val module = module {
    single { AppDatabase.getInstance(androidContext()) }
    single<ResourceDataSource> { ResourceDataSourceImpl(get()) }

    factory { NewHabitViewModel(get(), get()) }
    single<HabitDataSource>(named("local")) { LocalHabitDataSource(get()) }
    single<HabitDataSource>(named("remote")) { RemoteHabitDataSource() }
    single {
        HabitRepository(
            get(named("local")),
            get(named("remote"))
        )
    }
}