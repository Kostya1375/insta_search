package ua.com.dowell.instasearch.presenter.impl

import android.widget.SeekBar
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.rest.Api
import ua.com.dowell.instasearch.presenter.ProfilePresenter
import ua.com.dowell.instasearch.view.fragment.profile.ProfileView

/**
 * Created by kosty on 31.01.2018.
 */
class ProfilePresenterImpl(
        private val api: Api,
        private val accountHelper: AccountHelper
) : ProfilePresenter {

    private val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val fixedProgress = if (progress <= 5) {
                seekBar?.progress = progress + 5
                progress + 5
            } else progress

            accountHelper.setDistanceSettings(fixedProgress)
            view?.setDistanceValue(fixedProgress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
    }
    private var view: ProfileView? = null

    override fun setView(view: ProfileView) {
        this.view = view
        if (this.view != null) onViewSet()
    }

    private fun onViewSet() {
        launch {
            val user = api.myInfo().await()
            val distance = accountHelper.getDistanceSettings()
            launch(UI) {
                view?.fillViews(user)
                view?.setDistanceBarValue(distance)
                view?.setDistanceListener(seekBarListener)
                view?.setDistanceValue(distance)
                view?.hideProgressBar()
            }
        }
    }

    override fun disposeView() {
        view = null
    }
}