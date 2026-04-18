package com.example.androidtasks.FourthModule

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class CustomRectangleView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    init {
        isClickable = true
    }
    private var progress = 0
    private var color = Color.BLACK

    private val paint = android.graphics.Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = android.graphics.Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 6f

        canvas.drawRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            paint
        )

        val fillWidth = width * (progress / 100f)
        paint.style = android.graphics.Paint.Style.FILL
        paint.color = color

        canvas.drawRect(
            0f,
            0f,
            fillWidth,
            height.toFloat(),
            paint
        )
    }

    override fun performClick(): Boolean {
        super.performClick()

        progress += 10
        if (progress > 100) progress = 0
        color = randomColor()
        invalidate()
        return true
    }

    private fun randomColor(): Int {
        return Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
    }
}
