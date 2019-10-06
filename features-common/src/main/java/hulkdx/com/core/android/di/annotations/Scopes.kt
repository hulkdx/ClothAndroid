package hulkdx.com.core.android.di.annotations

import javax.inject.Scope

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScoped
