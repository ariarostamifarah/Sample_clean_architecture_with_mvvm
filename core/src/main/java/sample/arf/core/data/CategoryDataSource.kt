package sample.arf.core.data

import sample.arf.core.domain.Category
import sample.arf.core.domain.User

interface CategoryDataSource {


    suspend fun Add(category: Category)

    suspend fun Update(category: Category)

    suspend fun Remove(category: Category)

    suspend fun GetAll():List<Category>

    suspend fun GetById(id:Int):Category

    suspend fun GetByName(name:String):Category

    suspend fun GetCount():Int




}