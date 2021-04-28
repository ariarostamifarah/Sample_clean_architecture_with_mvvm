package sample.arf.clean_architecture_with_mvvm.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import sample.arf.clean_architecture_with_mvvm.framework.Util.FinishInitialData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [UserEntitiy::class, CategoryEntity::class], version = 1, exportSchema = false)
public abstract class DatabaseM : RoomDatabase() {

    abstract fun getDaoUser(): DaoUser
    abstract fun getDaoCategory(): DaoCategory


    companion object {

        @Volatile
        private var instance: DatabaseM? = null

        private fun builder(context: Context): DatabaseM =
            Room.databaseBuilder(context, DatabaseM::class.java, "DATABASE_ME")
                .fallbackToDestructiveMigration()
                .addCallback(callback)
                .build()


        fun getInstance(context: Context): DatabaseM =
            (instance ?: builder(context).also { instance = it })


        private val callback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)



                initializeData()


            }
        }

        var finishInitialData:FinishInitialData? = null

        private fun initializeData() {
            CoroutineScope(Dispatchers.Main).launch {



                var categoryEntity = CategoryEntity(_cate_name = "")

                categoryEntity.cate_name = "Android Developer"

                Companion.instance?.getDaoCategory()?.Add(categoryEntity)

                categoryEntity.cate_name = "Web Developer"

                Companion.instance?.getDaoCategory()?.Add(categoryEntity)

                categoryEntity.cate_name = "Ios Developer"


                Companion.instance?.getDaoCategory()?.Add(categoryEntity)

                categoryEntity.cate_name = "Linux Developer"


                Companion.instance?.getDaoCategory()?.Add(categoryEntity)


                finishInitialData?.loadData()

            }
        }


    }


    fun initializeData() {


    }


}