package hulkdx.com.features.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.features.home.R
import hulkdx.com.features.home.model.Cloth

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothViewHolder(
        itemView: View,
        private val mImageLoader: ImageLoader
): RecyclerView.ViewHolder(itemView) {

    private val mClothImageView:     ImageView = itemView.findViewById(R.id.clothImageView)

    fun bind(cloth: Cloth) {
        
        // Cloth
        mImageLoader.loadImage(cloth.imageUrl, mClothImageView)

    }

}