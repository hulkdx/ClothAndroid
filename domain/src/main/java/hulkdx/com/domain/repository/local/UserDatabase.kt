package hulkdx.com.domain.repository.local

import hulkdx.com.domain.entities.UserEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
interface UserDatabase {

    fun saveUser(user: UserEntity)
    fun getUser(): UserEntity?

}
