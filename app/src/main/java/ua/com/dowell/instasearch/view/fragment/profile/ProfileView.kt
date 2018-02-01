package ua.com.dowell.instasearch.view.fragment.profile

import android.widget.SeekBar
import ua.com.dowell.instasearch.model.pojo.User
import ua.com.dowell.instasearch.view.BaseView

/**
 * Created by kosty on 31.01.2018.
 */
interface ProfileView : BaseView {
    fun fillViews(user: User)
    fun hideProgressBar()
    fun setDistanceBarValue(value: Int)
    fun setDistanceListener(listener: SeekBar.OnSeekBarChangeListener)
    fun setDistanceValue(value: Int)
}