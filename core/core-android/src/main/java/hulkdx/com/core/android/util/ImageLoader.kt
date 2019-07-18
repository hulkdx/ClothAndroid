package hulkdx.com.core.android.util

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/07/2019.
 *
 * A placeholder for loading Images into views. In case in the future we want to replace Glide with
 * our own implementations.
 *
 * Note: Is this needs to be a ViewModel? or a UseCase?
 */
fun loadUrlIntoImageView(url: String, imageView: ImageView) {
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}
