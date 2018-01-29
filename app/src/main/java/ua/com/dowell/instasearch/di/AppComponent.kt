package ua.com.dowell.instasearch.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ua.com.dowell.instasearch.MainApplication
import ua.com.dowell.instasearch.model.BindModule
import ua.com.dowell.instasearch.model.impl.ApiImpl
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
    AppModule::class,
    ApiImpl::class
])
interface AppComponent : AndroidInjector<MainApplication> {

    override fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(mainApplication: MainApplication): Builder

        fun build(): AppComponent
    }
}