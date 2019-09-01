package hulkdx.com.data.firebase.model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

/**
 * Created by Mohammad Jafarzadeh Rezvan on 01/09/2019.
 */
internal sealed class ReadDatabaseResult {
    data class Error(val databaseError: DatabaseError): ReadDatabaseResult()
    data class Success(val databaseSnapshot: DataSnapshot): ReadDatabaseResult()
}