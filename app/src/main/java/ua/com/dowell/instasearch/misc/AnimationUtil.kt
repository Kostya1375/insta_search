package ua.com.dowell.instasearch.misc

import android.animation.FloatEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator

/**
 * Created by kosty on 01.02.2018.
 */
fun View.showScaled(finishListener: (() -> Unit)? = null) {
    val startValue = 0F
    val endValue = 1000F
    val valueAnimator = ValueAnimator.ofFloat(startValue, endValue)
    valueAnimator.setEvaluator(FloatEvaluator())
    valueAnimator.setInterpolator(AccelerateInterpolator())
    valueAnimator.duration = 150
    valueAnimator.addUpdateListener {
        val floatValue = it.animatedValue as Float / 1000
        if (floatValue == startValue) visibility = View.VISIBLE
        scaleX = floatValue
        scaleY = floatValue
        if (floatValue == endValue) finishListener?.invoke()
    }
    valueAnimator.start()
}

fun View.hideScaled(visibilityStatus: Int = View.GONE, finishListener: (() -> Unit)? = null) {

    val startValue = 1000F
    val endValue = 0F
    val valueAnimator = ValueAnimator.ofFloat(startValue, endValue)
    valueAnimator.setEvaluator(FloatEvaluator())
    valueAnimator.setInterpolator(AccelerateInterpolator())
    valueAnimator.duration = 150
    valueAnimator.addUpdateListener {
        val floatValue = it.animatedValue as Float / 1000
        scaleX = floatValue
        scaleY = floatValue
        if (floatValue == endValue) {
            visibility = visibilityStatus
            finishListener?.invoke()
        }
    }
    valueAnimator.start()
}