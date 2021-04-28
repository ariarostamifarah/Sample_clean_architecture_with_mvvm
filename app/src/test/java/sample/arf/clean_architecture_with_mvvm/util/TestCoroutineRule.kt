package sample.arf.clean_architecture_with_mvvm.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class TestCoroutineRule:TestRule{


    private val dispatcher = TestCoroutineDispatcher()

    private var testScope = TestCoroutineScope(dispatcher)

    override fun apply(base: Statement?, description: Description?) = object : Statement() {


        override fun evaluate() {

            Dispatchers.setMain(dispatcher)

            base?.evaluate()

            Dispatchers.resetMain()
            testScope.cleanupTestCoroutines()

        }


    }

    fun runScopeTest(block:suspend TestCoroutineScope.() -> Unit){

        testScope.runBlockingTest { block() }

    }


}