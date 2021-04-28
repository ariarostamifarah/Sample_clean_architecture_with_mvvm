package sample.arf.core.data

import sample.arf.core.domain.User

interface UserDataSource {


    suspend fun Add(user: User)

    suspend fun Update(user: User)

    suspend fun Remove(user: User)

    suspend fun GetAll():List<User>

    suspend fun GetById(id:Long):User


    suspend fun GetByCategoryId(categoryId:Int):List<User>




}