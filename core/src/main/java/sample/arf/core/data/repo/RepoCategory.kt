package sample.arf.core.data.repo

import sample.arf.core.data.CategoryDataSource
import sample.arf.core.domain.Category
import javax.inject.Inject

class RepoCategory(private val categoryDataSource: CategoryDataSource) {


    suspend fun Add(category: Category) = categoryDataSource.Add(category)

    suspend fun Update(category: Category) = categoryDataSource.Update(category)

    suspend fun Remove(category: Category) = categoryDataSource.Remove(category)

    suspend fun GetAll():List<Category> = categoryDataSource.GetAll()

    suspend fun GetById(id:Int): Category = categoryDataSource.GetById(id)

    suspend fun GetByName(name:String):Category = categoryDataSource.GetByName(name)

    suspend fun GetCount():Int = categoryDataSource.GetCount()



}