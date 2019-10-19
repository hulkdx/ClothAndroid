package hulkdx.com.features.common.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/07/2019.
 *
 * A placeholder for loading Images into views. In case in the future we want to replace Glide with
 * our own implementations.
 *
 * Note: currently this class is not Singleton
 */
class ImageLoader @Inject constructor() {

    fun loadImage(url: String,
                  imageView: ImageView,
                  isCircleCropTransform: Boolean = false,
                  placeholderDrawable: Drawable? = null) {
        var request = Glide.with(imageView.context)
                .load(url)

        if (placeholderDrawable != null) {
            request = request.placeholder(placeholderDrawable).apply(RequestOptions.circleCropTransform())
        }

        if (isCircleCropTransform) {

            request = request.apply(RequestOptions.circleCropTransform())
        }

        request.into(imageView)
    }
}
