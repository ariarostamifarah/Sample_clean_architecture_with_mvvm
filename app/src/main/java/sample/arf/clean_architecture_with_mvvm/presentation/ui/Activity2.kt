package sample.arf.clean_architecture_with_mvvm.presentation.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.R
import sample.arf.clean_architecture_with_mvvm.databinding.Activity2Binding
import sample.arf.clean_architecture_with_mvvm.framework.MainViewModel.MainViewModelFactory
import sample.arf.clean_architecture_with_mvvm.framework.Util.FinishActivity
import sample.arf.clean_architecture_with_mvvm.framework.app.App
import sample.arf.clean_architecture_with_mvvm.presentation.viewmodel.Activity2Viewmodel
import java.util.*
import javax.inject.Inject

class Activity2 : AppCompatActivity() , FinishActivity {


    private lateinit var activity2Binding: Activity2Binding

    private lateinit var viewModel: Activity2Viewmodel


    private lateinit var user: User

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        viewModel = ViewModelProviders.of(this@Activity2, mainViewModelFactory)
            .get(Activity2Viewmodel::class.java)


        activity2Binding = DataBindingUtil.setContentView(this, R.layout.activity_2)


        activity2Binding.clickHandler = ClickHandler(this)


        viewModel.finishActivity = this@Activity2


        viewModel.userEntitiy.observe(this , androidx.lifecycle.Observer {

            activity2Binding.user = it
        })


        showData()


    }

    private fun showData() {

        var data = intent


        if (data.hasExtra("state")) {


            if (Objects.equals(data.getStringExtra("state"), "update")) {

                title = "Update Developer"

                user = data.getSerializableExtra("user") as User


                viewModel.userToUserEntity(user)


            } else if (Objects.equals(data.getStringExtra("state"), "add")) {

                title = "Add Developer"

                viewModel.restUserEntity()


            }


        }


    }

    private fun addUserOrUpdateUser() {


        var intentData = intent

        if (intentData.hasExtra("state") && Objects.equals(
                intentData.getStringExtra("state"),
                "add"
            )
        ) {


            viewModel.addUserBycategoryId(intentData.getIntExtra("cate_id", 0))




        } else if (intentData.hasExtra("state") && Objects.equals(
                intentData.getStringExtra("state"),
                "update"
            )
        ) {


            viewModel.updateUserByUserEntity()




        }


    }


    inner class ClickHandler(context: Context) {


        fun OnClick_submit(view: View?) {

            addUserOrUpdateUser()
        }


    }

    override fun Onfinish() {

        setResult(Activity.RESULT_OK)
        finish()
    }


}