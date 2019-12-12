package hulkdx.com.domain.entities

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/12/2019.
 */
data class ClothWithCategoryEntity(
    val clothEntity: ClothEntity,
    val categoryEntity: CategoryEntity?
)
