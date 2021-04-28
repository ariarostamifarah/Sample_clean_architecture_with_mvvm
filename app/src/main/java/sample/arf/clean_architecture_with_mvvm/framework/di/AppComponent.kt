package sample.arf.clean_architecture_with_mvvm.framework.di

import android.app.Application
import sample.arf.clean_architecture_with_mvvm.framework.di.Module.FrameworkModule
import sample.arf.clean_architecture_with_mvvm.framework.di.Module.IntractorsModule
import sample.arf.clean_architecture_with_mvvm.framework.di.Module.RepoModule
import sample.arf.clean_architecture_with_mvvm.presentation.ui.Activity2
import sample.arf.clean_architecture_with_mvvm.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(FrameworkModule::class ,
    IntractorsModule::class ,
    RepoModule::class
))
interface AppComponent {



    fun inject(mainActivity: MainActivity):MainActivity

    fun inject(activity2: Activity2):Activity2



    @Component.Factory
    interface Factory{


        fun create(@BindsInstance application: Application):AppComponent

    }



}