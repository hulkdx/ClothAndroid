package hulkdx.com.domain.repository.remote

import hulkdx.com.domain.entities.CategoryEntity
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.entities.ClothesEntity
import java.io.IOException
import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 *
 * @see AuthException: should throw if auth fails.
 * @see IOException: should throw if network fails.
 * @see Exception: should throw if general exception happens (a bug?!)
 *
 */

interface CategoryEndPoint {

    @Throws(Exception::class)
    fun getAllCategories(): List<CategoryEntity>

}
