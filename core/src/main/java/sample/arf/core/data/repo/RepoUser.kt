package sample.arf.core.data.repo

import sample.arf.core.data.UserDataSource
import sample.arf.core.domain.User
import javax.inject.Inject

class RepoUser(private val userDataSource: UserDataSource) {


    suspend fun Add(user: User) = userDataSource.Add(user)

    suspend fun Update(user: User) = userDataSource.Update(user)

    suspend fun Remove(user: User) = userDataSource.Remove(user)

    suspend fun GetAll():List<User> = userDataSource.GetAll()

    suspend fun GetById(id:Long):User = userDataSource.GetById(id)


    suspend fun GetByCategoryId(categoryId:Int):List<User> = userDataSource.GetByCategoryId(categoryId)





}