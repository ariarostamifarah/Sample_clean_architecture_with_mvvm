package sample.arf.clean_architecture_with_mvvm.framework.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import sample.arf.core.domain.CategoryFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoCategoryTest {


    lateinit var db: DatabaseM


    @Before
    fun initDatabase() {


        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            DatabaseM::class.java
        ).build()


    }

    @After
    fun closeDb() {

        db.close()

    }


    @Test
    fun AddCategory_And_Category_IsNotEmpty() {

        var allCategory: List<CategoryEntity> = ArrayList<CategoryEntity>()

         CoroutineScope(Dispatchers.IO).launch {

            db.getDaoCategory().Add(CategoryFactory.create().toCategoryEntitiy())

            allCategory = db.getDaoCategory().GetAll()

            assert(allCategory.isNotEmpty())
        }


    }

    @Test
    fun UpdateCategory(){

        val updateName = "AndroidTestUpdate"

        CoroutineScope(Dispatchers.IO).launch {

            //add----------


            db.getDaoCategory().Add(CategoryFactory.create().toCategoryEntitiy())

            var category = db.getDaoCategory().GetByName(CategoryFactory.cate_name)

            //update----------


            category.cate_name = updateName

            db.getDaoCategory().Update(category)

            //assert----------

            val categoryUpdate = db.getDaoCategory().GetByName(CategoryFactory.cate_name)

            assert(categoryUpdate.cate_name == updateName)


        }





    }


}