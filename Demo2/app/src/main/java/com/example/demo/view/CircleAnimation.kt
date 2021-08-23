package com.example.demo.view

import android.view.animation.Animation
import android.view.animation.Transformation


class CircleAnimation(private val circleView: CircleView, newAngle: Int) : Animation() {
    private val oldAngle: Float = circleView.getArcAngle()
    private val newAngle: Float = newAngle.toFloat()
    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle = 0 + (newAngle - oldAngle) * interpolatedTime
        circleView.setArcAngle(angle)
        circleView.requestLayout()
    }



}