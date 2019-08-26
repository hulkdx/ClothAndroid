package hulkdx.com.core.android.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Mohammad Jafarzadeh Rezvan on 07/08/2019.
 *
 * The reason for this function is according to this video:
 * https://youtu.be/NQUv93Hvp0o?t=284
 * It should be different and it should be:
 * removeObservers(fragment.viewLifecycleOwner)
 * observe(fragment.viewLifecycleOwner, observer)
 *
 * TODO: research this.
 */
fun <T> LiveData<T>.observeFragment(fragment: Fragment, observer: Observer<T>) {
    observe(fragment.viewLifecycleOwner, observer)
}