package sample.arf.clean_architecture_with_mvvm.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.framework.Intractors
import sample.arf.clean_architecture_with_mvvm.framework.MainViewModel.MainViewModel
import sample.arf.clean_architecture_with_mvvm.framework.Util.FinishActivity
import sample.arf.clean_architecture_with_mvvm.framework.db.UserEntitiy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class Activity2Viewmodel(application: Application, intractors: Intractors) :
    MainViewModel(application, intractors) {

    var userById: MutableLiveData<User> = MutableLiveData()
    var userEntitiy: MutableLiveData<UserEntitiy> = MutableLiveData()


    lateinit var finishActivity : FinishActivity

    fun addUser(user: User) = viewModelScope.launch((Dispatchers.IO)) { intractors.addUser(user) }


    fun updateUser(user: User) =
            viewModelScope.launch((Dispatchers.IO)) { intractors.updateUser(user) }


    fun getUserById(id: Long): MutableLiveData<User> {


        CoroutineScope(Dispatchers.Main).launch {

            userById.value = async { intractors.getUserById(id) }.await()

        }


        return userById


    }


    fun addUserBycategoryId(categoryId: Int) {

        if (userEntitiy.value?.name != "" && userEntitiy.value?.email != "" && userEntitiy.value?.country != "") {


            userEntitiy.value?.data = Calendar.getInstance().time.toString()
            userEntitiy.value?.category_id = categoryId

            addUser(UserEntitiy.toUser(userEntitiy.value!!))

            finishActivity?.Onfinish()

        }


    }

    fun updateUserByUserEntity() {

        if (userEntitiy.value?.name != "" && userEntitiy.value?.email != "" && userEntitiy.value?.country != "") {

            userEntitiy.value?.data = Calendar.getInstance().time.toString()

            updateUser(UserEntitiy.toUser(userEntitiy.value!!))

            finishActivity?.Onfinish()

        }

    }


    fun userToUserEntity(user: User) {

        userEntitiy.value =  UserEntitiy.toUserEntitiy(user)



    }


    fun restUserEntity(){

        userEntitiy.value = UserEntitiy( _id = 0 ,_name = "", _email = "", _country = "", _data = "", _category_id = 0)

    }


}