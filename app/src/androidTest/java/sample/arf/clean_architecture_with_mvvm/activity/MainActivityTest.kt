package sample.arf.clean_architecture_with_mvvm.ui.activity

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import sample.arf.core.domain.CategoryFactory
import sample.arf.clean_architecture_with_mvvm.R
import sample.arf.clean_architecture_with_mvvm.base_test.BaseActivityTest
import sample.arf.clean_architecture_with_mvvm.framework.RoomCategoryDataSource
import sample.arf.clean_architecture_with_mvvm.presentation.ui.MainActivity
import sample.arf.clean_architecture_with_mvvm.presentation.ui.adapter.AdapterRecyclerview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = BaseActivityTest(MainActivity::class.java, true, false)


    @Before
    fun InsertData() {

        CoroutineScope(Dispatchers.IO).launch {

            val NumberOfCategory = RoomCategoryDataSource(activityRule.getContext()).GetByName(CategoryFactory.cate_name)

            if (NumberOfCategory == null) {

                RoomCategoryDataSource(activityRule.getContext()).Add(CategoryFactory.create())
            }


        }


    }

    @Test
    fun InitSpinnerTest() {
        activityRule.launchActivity()


        onView(withId(R.id.spinner)).check(matches(isDisplayed())).perform(click())

        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`(CategoryFactory.cate_name)
            )
        ).atPosition(
            0
        ).perform(
            click()
        )



    }


    @Test
    fun AddDataToRecyclerview_AND_DeleteFromRecyclerview() {
        activityRule.launchActivity()




        onView(withId(R.id.spinner)).check(matches(isDisplayed())).perform(click())
        onData(anything()).atPosition(0).perform(click())

        //Add

        for (i in 1..8) {

            onView(withId(R.id.floatAction)).check(matches(isDisplayed())).perform(click())

            onView(withId(R.id.edt_email)).check(matches(isDisplayed())).perform(
                typeText("$i" + "_example@gmail.com")
            )

            onView(withId(R.id.edt_name)).check(matches(isDisplayed())).perform(
                typeText("$i" + "_exampleName")
            )

            onView(withId(R.id.edt_country)).check(matches(isDisplayed())).perform(
                typeText("$i" + "_exampleCountry")
            )




            onView(withId(R.id.Activity2_scrollView)).check(matches(isDisplayed())).perform(swipeUp())
            onView(isRoot()).perform(waitFor(500))
            onView(withId(R.id.btn_submit)).check(matches(isDisplayed())).perform(click())

          //  pressBack()


        }

        //Delete

        for (i in 1..5) {

            onView(withId(R.id.recyclerview)).check(matches(isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<AdapterRecyclerview.Holder>(
                    0,
                    swipeRight()
                )
            )
            onView(isRoot()).perform(waitFor(500))


        }


    }


    fun waitFor(millis: Long): ViewAction? {


        return object : ViewAction {
            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }


}