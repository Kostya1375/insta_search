package ua.com.dowell.instasearch.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragment
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragmentModule

/**
 * Created by kosty on 24.01.2018.
 */
@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [InstagramFragmentModule::class])
    fun instagramFragment(): InstagramFragment
}