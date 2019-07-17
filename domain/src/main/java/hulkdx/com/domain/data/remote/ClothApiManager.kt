package hulkdx.com.domain.data.remote

import hulkdx.com.domain.model.Cloth
import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

interface ClothApiManager {

    @Throws(Exception::class)
    fun getCloths(): List<Cloth>

}
