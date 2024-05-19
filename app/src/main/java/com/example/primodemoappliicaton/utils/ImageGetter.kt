package com.example.primodemoappliicaton.utils

import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import coil.Coil
import coil.ImageLoader
import coil.request.ImageRequest
open class CoilImageGetter(
    private val textView: TextView,
    private val imageLoader: ImageLoader = Coil.imageLoader(textView.context),
    private val sourceModifier: ((source: String) -> String)? = null,
    private val fixedImageWidth: Int,
) : Html.ImageGetter {

    override fun getDrawable(source: String): Drawable {
        val finalSource = sourceModifier?.invoke(source) ?: source

        val drawablePlaceholder = DrawablePlaceHolder()
        imageLoader.enqueue(ImageRequest.Builder(textView.context).data(finalSource).apply {
            target { drawable ->
                drawablePlaceholder.updateDrawable(fixedImageWidth, drawable)
                textView.text = textView.text
            }
        }.build())
        return drawablePlaceholder
    }

    @Suppress("DEPRECATION")
    private class DrawablePlaceHolder : BitmapDrawable() {

        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        fun updateDrawable(width: Int, drawable: Drawable) {
            this.drawable = drawable
            val aspectRatio = drawable.intrinsicHeight.toFloat() / drawable.intrinsicWidth.toFloat()
            val height = (width.toFloat() * aspectRatio).toInt()
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
        }
    }
}