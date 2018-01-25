package ua.com.dowell.instasearch.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ua.com.dowell.instasearch.MainApplication
import ua.com.dowell.instasearch.model.AccountHelper
import ua.com.dowell.instasearch.model.InstagramModel
import javax.inject.Singleton

/**
 * Created by kosty on 23.01.2018.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    BindModule::class,
    AppModule::class
])
interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(application: MainApplication)

    fun getAccountHelper(): AccountHelper
    fun getInstagramModel(): InstagramModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(mainApplication: MainApplication): Builder

        fun build(): AppComponent
    }
}