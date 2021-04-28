package sample.arf.clean_architecture_with_mvvm.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import sample.arf.core.domain.Category
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.framework.Intractors
import sample.arf.clean_architecture_with_mvvm.framework.MainViewModel.MainViewModel
import sample.arf.clean_architecture_with_mvvm.framework.Util.FinishInitialData
import sample.arf.clean_architecture_with_mvvm.framework.db.DatabaseM
import kotlinx.coroutines.*

class MainActivityViewmodel(application: Application, intractors: Intractors) :
    MainViewModel(application, intractors) , FinishInitialData {


    init {
        DatabaseM.finishInitialData =this
    }

    var users: MutableLiveData<MutableList<User>> = MutableLiveData()
    var userById: MutableLiveData<User> = MutableLiveData()
    var categorys: MutableLiveData<MutableList<Category>> = MutableLiveData()
    var usersByCategoryId: MutableLiveData<MutableList<User>> = MutableLiveData()
    var spinnerPos:Int = 0




    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) { intractors.addUser(user) }

    fun updateUser(user: User) =
        viewModelScope.launch(Dispatchers.IO) { intractors.updateUser(user) }


    fun removeUser(user: User) =
        viewModelScope.launch(Dispatchers.IO) { intractors.removeUser(user) }



    fun loadAllUser() {

        viewModelScope.launch(Dispatchers.Main) {

            users.value = async(Dispatchers.IO) { intractors.getAllUser().toMutableList() }.await()

        }
    }


    fun loadUserById(id: Long) {

        viewModelScope.launch(Dispatchers.Main) {

            userById.value = async(Dispatchers.IO) { intractors.getUserById(id) }.await()

        }
    }


    fun loadAllCategory() {


        viewModelScope.launch(Dispatchers.Main){

            categorys.value =  async(Dispatchers.IO) { intractors.getAllCategory().toMutableList() }.await()

        }
    }


    fun loadUsersByCategoryId(id: Int) {

        viewModelScope.launch(Dispatchers.Main) {

            usersByCategoryId.value =
                async(Dispatchers.IO) { intractors.getUserByCategoryId(id).toMutableList() }.await()

        }
    }


    fun removeCategory(category: Category) {

        viewModelScope.launch(Dispatchers.IO) { intractors.removeCategory(category) }

        loadAllCategory()
    }

    override fun loadData() {

        loadAllCategory()

    }


}