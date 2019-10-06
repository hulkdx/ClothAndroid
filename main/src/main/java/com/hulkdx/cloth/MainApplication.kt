package com.hulkdx.cloth

import android.app.Application
import android.content.Context
import com.hulkdx.cloth.di.ApplicationComponent
import com.hulkdx.cloth.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import hulkdx.com.core.android.BuildConfig
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */

// region Statics -------------------------------------------------------------------------------

fun applicationComponent(context: Context): ApplicationComponent {
    return (context.applicationContext as MainApplication).applicationComponent
}

// endregion Statics --------------------------------------------------------------------------

class MainApplication : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    internal lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initLeakDetection()
    }

    private fun initDagger() {
        DaggerApplicationComponent.builder()
                .applicationContext(this)
                .build()
                .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
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
