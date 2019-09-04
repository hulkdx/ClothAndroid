package hulkdx.com.domain.repository.local

import hulkdx.com.domain.entities.ClothesEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
interface ClothDatabase {

    fun saveAll(clothes: ClothesEntity)
    fun getAll(): ClothesEntity?

}
