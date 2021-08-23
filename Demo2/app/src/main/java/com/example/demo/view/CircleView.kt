package com.example.demo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.demo.R


class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint: Paint = Paint()
    private val mRect: RectF
    private var arcAngle = 0f


    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 20F
        mPaint.color = resources.getColor(R.color.buttonColor)
        mRect = RectF(20F, 20F, 220F, 220F)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawArc(mRect, 270F, arcAngle, false, mPaint)

    }
    fun getArcAngle(): Float {
        return arcAngle
    }

    fun setArcAngle(arcAngle: Float) {
        this.arcAngle = arcAngle
    }
}