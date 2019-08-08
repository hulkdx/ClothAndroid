package hulkdx.com.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.features.home.R
import hulkdx.com.features.home.model.Cloth
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothAdapter @Inject constructor(private val mImageLoader: ImageLoader): RecyclerView.Adapter<ClothViewHolder>() {

    private var mClothes = listOf<Cloth>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cloth, parent, false)
        return ClothViewHolder(itemView, mImageLoader)
    }

    override fun getItemCount(): Int = mClothes.size

    override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
        val model = mClothes[position]
        holder.bind(model)
    }

    // region Data ---------------------------------------------------------------------------------

    fun updateClothes(clothes: List<Cloth>) {
        mClothes = clothes
    }

    // endregion Data ------------------------------------------------------------------------------

}
