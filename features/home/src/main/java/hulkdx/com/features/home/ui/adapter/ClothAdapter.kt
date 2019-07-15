package hulkdx.com.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hulkdx.com.features.home.R

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */
internal class ClothAdapter: RecyclerView.Adapter<ClothViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cloth, parent, false)
        return ClothViewHolder(itemView)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
    }

}
