package hulkdx.com.features.explore.view.list

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.features.explore.R
import hulkdx.com.features.explore.model.Cloth

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothViewHolder(
        rootView: View,
        private val mImageLoader: ImageLoader
): RecyclerView.ViewHolder(rootView) {

    private val mClothImageView: ImageView = rootView.findViewById(R.id.clothImageView)

    fun bind(cloth: Cloth) {
        // Cloth
        mImageLoader.loadImage(cloth.imageUrl, mClothImageView)
    }

}