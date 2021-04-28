package sample.arf.clean_architecture_with_mvvm.framework.db

import androidx.room.*

@Dao
interface DaoCategory {



    @Insert
    suspend fun Add(category: CategoryEntity)

    @Update
    suspend fun Update(category: CategoryEntity)

    @Delete
    suspend fun Remove(category: CategoryEntity)

    @Query("SELECT * FROM tb_category")
    suspend fun GetAll():List<CategoryEntity>

    @Query("SELECT * FROM tb_category WHERE _id ==:id")
    suspend fun GetById(id:Int):CategoryEntity

    @Query("SELECT COUNT(*) FROM tb_category")
    suspend fun GetCount():Int

    @Query("Select * FROM tb_category WHERE _cate_name ==:name")
    suspend fun GetByName(name:String):CategoryEntity



}