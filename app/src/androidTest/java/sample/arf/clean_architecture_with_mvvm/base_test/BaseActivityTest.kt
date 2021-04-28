package sample.arf.clean_architecture_with_mvvm.base_test

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule


class BaseActivityTest<T : Activity>(
    ActivityClass: Class<T>,
    TouchMode: Boolean,
    LaunchActivity: Boolean
) : ActivityTestRule<T>(ActivityClass , TouchMode , LaunchActivity) {


    fun launchActivity() = this.launchActivity(activityIntent)

    fun getContext():Context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext


    override fun getActivityIntent(): Intent {
        return Intent()
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()




    }

    override fun afterActivityLaunched() {
       super.afterActivityLaunched()



    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()



    }
}