package sample.arf.clean_architecture_with_mvvm.framework.app

import android.app.Application

import sample.arf.clean_architecture_with_mvvm.framework.di.AppComponent
import sample.arf.clean_architecture_with_mvvm.framework.di.DaggerAppComponent

class App :Application(){



    companion object{

        lateinit var appComponent:AppComponent

        fun getApplication() = this

    }

    override fun onCreate() {

        super.onCreate()


            appComponent = DaggerAppComponent.factory().create(this)



    }







}