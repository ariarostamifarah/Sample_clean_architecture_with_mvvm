package sample.arf.clean_architecture_with_mvvm.framework

import android.content.Context
import android.util.Log
import sample.arf.core.data.CategoryDataSource
import sample.arf.core.domain.Category
import sample.arf.clean_architecture_with_mvvm.framework.db.CategoryEntity
import sample.arf.clean_architecture_with_mvvm.framework.db.DatabaseM

class RoomCategoryDataSource(context: Context) : CategoryDataSource {

    private val cateDao = DatabaseM.getInstance(context).getDaoCategory()

    override suspend fun Add(category: Category) {

        cateDao.Add(CategoryEntity(category.cate_id , category.cate_name))
    }

    override suspend fun Update(category: Category) {

        cateDao.Update(CategoryEntity(category.cate_id , category.cate_name))

    }

    override suspend fun Remove(category: Category) {

        cateDao.Remove(CategoryEntity(category.cate_id , category.cate_name))
    }



    override suspend fun GetAll(): List<Category> {

        cateDao.GetAll().forEach{

            Log.i("myappv" , "${it.id} , ${it.cate_name}\n" )

        }

       return cateDao.GetAll()?.map {

            Category(it.id , it.cate_name)

        }


    }

    override suspend fun GetById(id: Int): Category = cateDao.GetById(id)?.let {

        Category(it.id , it.cate_name)
    }

    override suspend fun GetByName(name: String): Category  = cateDao.GetByName(name)?.let {

        Category(it.id , it.cate_name)
    }

    override suspend fun GetCount(): Int = cateDao.GetCount()


}