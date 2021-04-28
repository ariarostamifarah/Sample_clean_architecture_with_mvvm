package sample.arf.clean_architecture_with_mvvm.framework.db

import androidx.room.*


@Dao
interface DaoUser {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Add(user: UserEntitiy)

    @Update
    suspend fun Update(user: UserEntitiy)

    @Delete
    suspend fun Remove(user: UserEntitiy)

    @Query("SELECT * FROM tb_users")
    suspend fun GetAll():List<UserEntitiy>

    @Query("SELECT * FROM tb_users WHERE _id ==:id")
    suspend fun GetById(id:Long):UserEntitiy

    @Query("SELECT * FROM tb_users WHERE _category_id ==:categoryId")
    suspend fun GetByCategoryId(categoryId:Int):List<UserEntitiy>








}