package hulkdx.com.domain.di

import javax.inject.Qualifier

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */

// region Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BackgroundScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UiScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext

// endregion Qualifier
