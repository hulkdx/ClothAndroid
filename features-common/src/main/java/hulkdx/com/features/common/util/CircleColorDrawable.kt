package hulkdx.com.features.common.util

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable

class CircleColorDrawable(color: Int): ColorDrawable(color) {
    override fun draw(canvas: Canvas) {
        val radius = 90.0f // angle of round corners
        val clipPath = Path()
        val rect = RectF(bounds)
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.draw(canvas)
    }
}
