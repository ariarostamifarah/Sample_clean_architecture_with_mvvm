package sample.arf.clean_architecture_with_mvvm.ui.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import sample.arf.core.domain.Category
import sample.arf.core.domain.CategoryFactory
import sample.arf.core.domain.User
import sample.arf.core.domain.UserFactory
import sample.arf.core.usecases.category.GetAllCategory
import sample.arf.core.usecases.user.GetUserByCategoryId
import sample.arf.clean_architecture_with_mvvm.framework.Intractors
import sample.arf.clean_architecture_with_mvvm.presentation.viewmodel.MainActivityViewmodel
import sample.arf.clean_architecture_with_mvvm.util.TestCoroutineRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityViewmodelTest {


    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var viewmodel: MainActivityViewmodel

    @Mock
    lateinit var intractors: Intractors

    @Mock
    lateinit var getAllCategory: GetAllCategory

    @Mock
    lateinit var getUserByCategoryId: GetUserByCategoryId


    @Mock
    lateinit var application: Application

    @Mock
    lateinit var observerListCategory: Observer<MutableList<Category>>

    @Mock
    lateinit var observerListUser: Observer<MutableList<User>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifeCycle: LifecycleRegistry


    @Before
    fun initBeforeTest() {


        MockitoAnnotations.initMocks(this)

        viewmodel = MainActivityViewmodel(application, intractors)



       // lifeCycle = LifecycleRegistry(lifecycleOwner)

      //  whenever(lifecycleOwner.lifecycle).thenReturn(lifeCycle)

     //   lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_START)




    }


    @Test
    fun loadAllCategoryTest() {


        val list = CategoryFactory.createList()

        testCoroutineRule.runScopeTest {

            whenever(intractors.getAllCategory).thenReturn(getAllCategory)
            whenever(getAllCategory()).thenReturn(list)

            viewmodel.categorys.observeForever(observerListCategory)
            viewmodel.loadAllCategory()



            verify(observerListCategory).onChanged(

                list.toMutableList()
            )

            Assert.assertEquals(viewmodel.categorys.value?.get(2)?.cate_name, list.get(2).cate_name)

            viewmodel.categorys.removeObserver(observerListCategory)


        }

    }






    @Test
    fun loadUsersByCategoryId(){

        val id = 1
        val listUser:List<User> = listUserByCategoryId(id) as List<User>
        testCoroutineRule.runScopeTest {


            whenever(intractors.getUserByCategoryId).thenReturn(getUserByCategoryId)
            whenever(getUserByCategoryId(id)).thenReturn(listUser)


            viewmodel.usersByCategoryId.observeForever(observerListUser)
            viewmodel.loadUsersByCategoryId(id)

            delay(500)


            verify(observerListUser).onChanged(

                listUser.toMutableList()

            )


            viewmodel.usersByCategoryId.removeObserver(observerListUser)

        }






    }




    private fun listUserByCategoryId( id:Int):List<User?> {

        return UserFactory.createList().map {

            it.takeIf {
                it.category_id == id
            }?.let {
                User(it.id , it.name , it.email , it.country , it.data , it.category_id)
            }


        }


    }




}



