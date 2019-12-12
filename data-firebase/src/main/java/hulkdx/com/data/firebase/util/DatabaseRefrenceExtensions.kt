package hulkdx.com.data.firebase.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import hulkdx.com.data.firebase.AsyncToSync

/**
 * Created by Mohammad Jafarzadeh Rezvan on 30/09/2019.
 */
internal fun<T> DatabaseReference.convertToModel(mapper: (Map<String, Any>) -> (T)): T {
    return convert(mapper)
}

internal fun<T> DatabaseReference.convertListToModel(mapper: (List<Map<String, Any>>) -> (T)): T {
    return convert(mapper)
}

private inline fun<T, V> DatabaseReference.convert(mapper: (V) -> (T)): T {
    val asyncToSync = AsyncToSync<ReadDatabaseResult>()
    addListenerForSingleValueEvent(object: ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {
            asyncToSync.signalAll(ReadDatabaseResult.Error(databaseError))
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            asyncToSync.signalAll(ReadDatabaseResult.Success(dataSnapshot))
        }
    })
    when (val result = asyncToSync.await()) {
        is ReadDatabaseResult.Error -> throw result.databaseError.toException()
        is ReadDatabaseResult.Success -> {
            @Suppress("UNCHECKED_CAST")
            val value = result.databaseSnapshot.value as V
            return mapper(value)
        }
    }
}

internal sealed class ReadDatabaseResult {
    data class Error(val databaseError: DatabaseError): ReadDatabaseResult()
    data class Success(val databaseSnapshot: DataSnapshot): ReadDatabaseResult()
}
