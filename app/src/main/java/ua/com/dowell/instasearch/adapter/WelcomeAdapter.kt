package ua.com.dowell.instasearch.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ua.com.dowell.instasearch.R

/**
 * Created by kosty on 29.01.2018.
 */
class WelcomeAdapter : PagerAdapter() {

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return `object` == view
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val context = container?.context
        val view = View.inflate(context, R.layout.item_welcome, null) as TextView

        val textResId = getText(position)
        val backgroundResId = getBackground(position)

        view.setText(textResId)
        view.setBackgroundResource(backgroundResId)
        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        if (`object` is View) container?.removeView(`object`)
    }

    private fun getBackground(position: Int): Int {
        return when (position) {
            0 -> R.drawable.welcome_bg_0
            1 -> R.drawable.welcome_bg_1
            2 -> R.drawable.welcome_bg_2
            else -> R.drawable.welcome_bg_0
        }
    }


    override fun getCount(): Int = PAGE_COUNT
    private fun getText(position: Int): Int {
        return when (position) {
            0 -> R.string.welcome_text_0
            1 -> R.string.welcome_text_1
            2 -> R.string.welcome_text_2
            else -> R.string.welcome_text_0
        }
    }

    companion object {
        private const val PAGE_COUNT = 3
    }
}