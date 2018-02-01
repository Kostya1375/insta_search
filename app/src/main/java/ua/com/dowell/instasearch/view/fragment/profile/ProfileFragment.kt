package ua.com.dowell.instasearch.view.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.misc.CircleTransform
import ua.com.dowell.instasearch.model.pojo.User
import ua.com.dowell.instasearch.presenter.ProfilePresenter
import javax.inject.Inject

/**
 * Created by kosty on 31.01.2018.
 */
class ProfileFragment : DaggerFragment(), ProfileView {

    @Inject
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePresenter.setView(this)
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun setDistanceBarValue(value: Int) {
        distance_seek_bar.progress = value
    }

    override fun setDistanceValue(value: Int) {
        distance_value.text = value.toString()
    }

    override fun setDistanceListener(listener: SeekBar.OnSeekBarChangeListener) {
        distance_seek_bar.setOnSeekBarChangeListener(listener)
    }

    override fun fillViews(user: User) {
        Picasso.with(context)
                .load(user.avatar)
                .transform(CircleTransform())
                .into(avatar)
        full_name.text = user.fullName
        username.text = user.username
    }

    companion object {
        fun instance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}