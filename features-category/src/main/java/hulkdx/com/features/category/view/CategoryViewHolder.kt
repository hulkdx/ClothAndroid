package hulkdx.com.features.category.view

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.features.category.R
import hulkdx.com.domain.entities.CategoryBody
import hulkdx.com.features.common.util.ImageLoader

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class CategoryViewHolder(
        rootView: View,
        private val mImageLoader: ImageLoader
): RecyclerView.ViewHolder(rootView) {

    private val mClothImageView: ImageView = rootView.findViewById(R.id.clothImageView)

    fun bind(cloth: CategoryBody) {
        // Cloth
        mImageLoader.loadImage(cloth.imageUrl, mClothImageView)
    }

}