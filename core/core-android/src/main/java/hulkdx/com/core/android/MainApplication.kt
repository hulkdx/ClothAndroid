package hulkdx.com.core.android

import android.app.Application
import android.content.Context
import hulkdx.com.core.android.di.ApplicationComponent
import com.squareup.leakcanary.LeakCanary
import hulkdx.com.core.android.di.DaggerApplicationComponent

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */

// region Statics -------------------------------------------------------------------------------

fun applicationComponent(context: Context): ApplicationComponent {
    return (context.applicationContext as MainApplication).applicationComponent
}

// endregion Statics --------------------------------------------------------------------------

class MainApplication : Application() {

    internal lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initLeakDetection()
    }

    private fun initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationContext(this)
                .build()
    }

    private fun initLeakDetection() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }
    }

}
