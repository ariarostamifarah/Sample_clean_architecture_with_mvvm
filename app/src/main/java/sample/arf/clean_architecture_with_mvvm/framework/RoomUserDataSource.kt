package sample.arf.clean_architecture_with_mvvm.framework

import android.content.Context
import sample.arf.core.data.UserDataSource
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.framework.db.DatabaseM
import sample.arf.clean_architecture_with_mvvm.framework.db.UserEntitiy

class RoomUserDataSource(context: Context) : UserDataSource {

    private val userDao = DatabaseM.getInstance(context).getDaoUser()

    override suspend fun Add(user: User) {

        userDao.Add(UserEntitiy(user.id , user.name , user.email , user.country , user.data , user.category_id))

    }

    override suspend fun Update(user: User) {

        userDao.Update(UserEntitiy(user.id , user.name , user.email , user.country , user.data , user.category_id))

    }

    override suspend fun Remove(user: User) {

        userDao.Remove(UserEntitiy(user.id , user.name , user.email , user.country , user.data , user.category_id))

    }

    override suspend fun GetAll(): List<User> = userDao.GetAll()?.map {

        User(it.id , it.name , it.email , it.country , it.data , it.category_id)
    }



    override suspend fun GetById(id: Long): User = userDao.GetById(id)?.let {

        User(it.id , it.name , it.email , it.country , it.data , it.category_id)
    }



    override suspend fun GetByCategoryId(categoryId: Int): List<User> = userDao.GetByCategoryId(categoryId)?.map {

        User(it.id , it.name , it.email , it.country , it.data , it.category_id)

    }

}