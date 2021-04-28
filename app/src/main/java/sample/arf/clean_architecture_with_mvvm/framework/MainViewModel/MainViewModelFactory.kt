package sample.arf.clean_architecture_with_mvvm.framework.MainViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sample.arf.clean_architecture_with_mvvm.framework.Intractors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(var application: Application, var intractors: Intractors) : ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (MainViewModel::class.java.isAssignableFrom(modelClass)){

            return modelClass.getConstructor(Application::class.java , Intractors::class.java).newInstance(application , intractors)
        }
        else{

            throw IllegalStateException("ViewModel must extend MainViewModelFactory")
        }


    }
}