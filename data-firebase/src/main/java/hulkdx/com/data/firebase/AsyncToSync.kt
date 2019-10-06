package hulkdx.com.data.firebase

import java.util.concurrent.locks.ReentrantLock

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class AsyncToSync<T: Any> {

    private val mLock = ReentrantLock()
    private val mLockCondition = mLock.newCondition()
    private var mSignal = false
    private lateinit var mResult: T

    fun signalAll(result: T) {
        try {
            mLock.lock()
            mResult = result
            mSignal = true
            mLockCondition.signalAll()
        } finally {
            mLock.unlock()
        }
    }

    fun await(): T {
        try {
            mLock.lock()
            if (mSignal) {
                // do not need to wait
                return mResult
            }
            mLockCondition.await()
            return mResult
        } finally {
            mLock.unlock()
        }
    }

}
