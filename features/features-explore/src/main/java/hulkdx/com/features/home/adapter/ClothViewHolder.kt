package hulkdx.com.features.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.core.android.util.loadUrlIntoImageView
import hulkdx.com.features.home.R
import hulkdx.com.features.home.model.Cloth

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothViewHolder(
        itemView: View
): RecyclerView.ViewHolder(itemView) {

    private val mClothImageView: ImageView = itemView.findViewById(R.id.clothImageView)
    private val mClothPriceTextView: TextView = itemView.findViewById(R.id.clothPriceTextView)

    fun bind(cloth: Cloth) {
        loadUrlIntoImageView(cloth.imageUrl, mClothImageView)
        mClothPriceTextView.text = cloth.price
    }

}