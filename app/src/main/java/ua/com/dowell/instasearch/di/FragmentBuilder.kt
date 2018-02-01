package ua.com.dowell.instasearch.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragment
import ua.com.dowell.instasearch.view.fragment.instagram.InstagramFragmentModule
import ua.com.dowell.instasearch.view.fragment.main.MainFragment
import ua.com.dowell.instasearch.view.fragment.main.MainFragmentModule
import ua.com.dowell.instasearch.view.fragment.profile.ProfileFragment
import ua.com.dowell.instasearch.view.fragment.profile.ProfileFragmentModule
import ua.com.dowell.instasearch.view.fragment.welcome.WelcomeFragment
import ua.com.dowell.instasearch.view.fragment.welcome.WelcomeFragmentModule

/**
 * Created by kosty on 24.01.2018.
 */
@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [InstagramFragmentModule::class])
    fun instagramFragment(): InstagramFragment

    @ContributesAndroidInjector(modules = [WelcomeFragmentModule::class])
    fun welcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun mainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    fun profileFragment(): ProfileFragment
}