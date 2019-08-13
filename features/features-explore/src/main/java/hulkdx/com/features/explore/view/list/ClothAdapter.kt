package hulkdx.com.features.explore.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import hulkdx.com.core.android.util.ImageLoader
import hulkdx.com.features.explore.R
import hulkdx.com.features.explore.model.Cloth
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothAdapter @Inject constructor(
        private val mImageLoader: ImageLoader
): RecyclerView.Adapter<ClothViewHolder>() {

    private var mClothes = listOf<Cloth>()
    private var mClickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cloth, parent, false)
        val viewHolder = ClothViewHolder(rootView, mImageLoader)

        rootView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val listener = mClickListener
            if (position != NO_POSITION && listener != null) {
                val cloth: Cloth? = mClothes[position]
                if (cloth != null) {
                    listener.onClothClicked(position, cloth)
                }
            }
        }

        return viewHolder
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
    // region ClickListener ------------------------------------------------------------------------
    
    fun registerClickListener(clickListener: ClickListener) {
        mClickListener = clickListener
    }

    fun unregisterClickListener() {
        mClickListener = null
    }
    
    interface ClickListener {
        fun onClothClicked(position: Int, cloth: Cloth)
    }
    
    // endregion ClickListener ---------------------------------------------------------------------
}
