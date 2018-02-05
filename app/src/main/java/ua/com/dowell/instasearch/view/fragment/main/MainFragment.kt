package ua.com.dowell.instasearch.view.fragment.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.*
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ua.com.dowell.instasearch.R
import ua.com.dowell.instasearch.adapter.UserAdapter
import ua.com.dowell.instasearch.misc.hideScaled
import ua.com.dowell.instasearch.misc.replaceFragment
import ua.com.dowell.instasearch.misc.showScaled
import ua.com.dowell.instasearch.model.pojo.User
import ua.com.dowell.instasearch.presenter.MainFragmentPresenter
import ua.com.dowell.instasearch.view.fragment.profile.ProfileFragment
import javax.inject.Inject

/**
 * Created by kosty on 29.01.2018.
 */
class MainFragment : DaggerFragment(), MainView {

    @Inject
    lateinit var presenter: MainFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?,menuInflater:MenuInflater?) {
        menuInflater?.inflate(R.menu.menu_bottom, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_profile -> activity.replaceFragment(R.id.container, ProfileFragment.instance(), null)
            android.R.id.home -> activity.onBackPressed()
            else -> return false
        }
        return true
    }

    override fun showUsers(list: List<User>) {
        placeholder.visibility = View.GONE
        if (recycler_view.adapter == null) recycler_view.adapter = UserAdapter()
        val userAdapter = recycler_view.adapter as UserAdapter
        userAdapter.setUsers(list)
    }

    override fun showEmptyPlaceholder() {
        placeholder.visibility = View.VISIBLE
        progress_bar.hideScaled(View.INVISIBLE, {
            placeholder_image.setImageResource(R.drawable.ic_clear_gray_24dp)
            placeholder_image.showScaled()
        })
        placeholder_hint.setText(R.string.no_nearby_users)
    }

    override fun showError() {
        placeholder.visibility = View.VISIBLE
        progress_bar.hideScaled(View.INVISIBLE, {
            placeholder_image.setImageResource(R.drawable.ic_clear_red_24dp)
            placeholder_image.showScaled()
        })
        placeholder_hint.setText(R.string.something_went_wrong)
    }

    override fun requestPermissions() {
        val activity = activity
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(activity, permissions, PERMISSION_RC)
    }

    override fun checkPermissions(): Boolean {
        val context = context
        val fineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return fineLocationPermission == PackageManager.PERMISSION_GRANTED && coarseLocationPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_RC && (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            presenter.onPermissionGranted()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.startSpectate()
    }

    override fun onPause() {
        super.onPause()
        presenter.stopSpectate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.disposeView()
    }

    companion object {
        private const val PERMISSION_RC = 1101
        fun instance(): MainFragment {
            return MainFragment()
        }
    }
}