package sample.arf.clean_architecture_with_mvvm.presentation.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.R
import sample.arf.clean_architecture_with_mvvm.databinding.ActivityMainBinding
import sample.arf.clean_architecture_with_mvvm.framework.MainViewModel.MainViewModelFactory
import sample.arf.clean_architecture_with_mvvm.framework.app.App
import sample.arf.clean_architecture_with_mvvm.presentation.ui.adapter.AdapterRecyclerview
import sample.arf.clean_architecture_with_mvvm.presentation.viewmodel.MainActivityViewmodel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AdapterRecyclerview.OnclickItemListener {

    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var viewmodel: MainActivityViewmodel

    private lateinit var adapterRecyclerview: AdapterRecyclerview

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)


        viewmodel = ViewModelProviders.of(this@MainActivity, mainViewModelFactory)
            .get(MainActivityViewmodel::class.java)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)







        initSpinner()
        initRecyclerview()

        activityMainBinding.clickHandler = ClickHandler(this@MainActivity)


    }

    private fun initSpinner() {


            viewmodel.loadAllCategory()
        viewmodel.categorys.observe(this@MainActivity, Observer {
            val arrayAdapter =
                ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item)

            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            it.forEach {

                arrayAdapter.add(it.cate_name)

            }

            activityMainBinding.adapterSpinner = arrayAdapter

        })


    }


    private fun initRecyclerview() {


        adapterRecyclerview = AdapterRecyclerview(mutableListOf())

        activityMainBinding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(activityMainBinding.recyclerview)

        activityMainBinding.recyclerview.adapter = adapterRecyclerview

        adapterRecyclerview.onclickItemListener = this@MainActivity


    }


    val itemTouchHelper =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


                viewmodel.removeUser(adapterRecyclerview.users.get(viewHolder.adapterPosition))

                adapterRecyclerview.users.removeAt(viewHolder.adapterPosition)

                adapterRecyclerview.notifyItemRemoved(viewHolder.adapterPosition)

                adapterRecyclerview.notifyItemRangeChanged(
                    viewHolder.adapterPosition,
                    adapterRecyclerview.users.size
                )


            }


        }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            viewmodel.loadUsersByCategoryId(viewmodel.spinnerPos)
            viewmodel.usersByCategoryId.observe(this@MainActivity, Observer {



                    adapterRecyclerview.UpdateUsers(it)



                })


        }







    }

    inner class ClickHandler(val context: Context) {


        fun OnClick_floatAction(view: View) {


            val intent = Intent(this@MainActivity, Activity2::class.java)

            intent.putExtra("state", "add")
            intent.putExtra("cate_id", viewmodel.spinnerPos)
            startActivityForResult(intent, 100)



        }


        fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            viewmodel.spinnerPos = position + 1

            Log.i("myappv" , "position:$position")

            viewmodel.loadUsersByCategoryId(position + 1)
            viewmodel.usersByCategoryId.observe(this@MainActivity, Observer {



                adapterRecyclerview.UpdateUsers(it)

            })


        }


    }


    //onClick for item recyclerView

    override fun OnClick(user: User) {

        val intent = Intent(this@MainActivity, Activity2::class.java)

        intent.putExtra("state", "update")

        intent.putExtra("user", user)

        startActivityForResult(intent, 100)


    }


}