package hulkdx.com.features.category.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import hulkdx.com.features.category.R
import hulkdx.com.domain.entities.CategoryBody
import hulkdx.com.features.common.util.ImageLoader
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class CategoryAdapter @Inject constructor(
        private val mImageLoader: ImageLoader,
        private var mItems: List<CategoryBody>
): RecyclerView.Adapter<CategoryViewHolder>() {

    private var mClickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        val viewHolder = CategoryViewHolder(rootView, mImageLoader)

        rootView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val listener = mClickListener
            if (position != NO_POSITION && listener != null) {
                val item: CategoryBody? = mItems[position]
                if (item != null) {
                    listener.onItemClicked(position, item)
                }
            }
        }

        return viewHolder
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model = mItems[position]
        holder.bind(model)
    }

    // region ClickListener ------------------------------------------------------------------------
    
    fun registerClickListener(clickListener: ClickListener) {
        mClickListener = clickListener
    }

    fun unregisterClickListener() {
        mClickListener = null
    }
    
    internal interface ClickListener {
        fun onItemClicked(position: Int, item: CategoryBody)
    }
    
    // endregion ClickListener ---------------------------------------------------------------------
}
