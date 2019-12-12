package hulkdx.com.features.common.util

import android.content.Context
import android.content.res.Resources
import hulkdx.com.domain.di.ActivityContext
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 08/12/2019.
 */
class SystemResourceUtils @Inject constructor(
        @ActivityContext context: Context
) {

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels
}
