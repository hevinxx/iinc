package hevinxx.iinc

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Iinc : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Iinc)
            androidLogger(Level.DEBUG)
            modules(
                module
            )
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}