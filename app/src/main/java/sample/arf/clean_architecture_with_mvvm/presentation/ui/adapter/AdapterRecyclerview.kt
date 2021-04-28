package sample.arf.clean_architecture_with_mvvm.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sample.arf.core.domain.User
import sample.arf.clean_architecture_with_mvvm.R
import sample.arf.clean_architecture_with_mvvm.databinding.ItemsBinding
import sample.arf.clean_architecture_with_mvvm.framework.Util.UserDiffCallback

class AdapterRecyclerview( var users:MutableList<User>) : RecyclerView.Adapter<AdapterRecyclerview.Holder>() {





    interface OnclickItemListener{

        fun OnClick(user:User)

    }

    var onclickItemListener: OnclickItemListener? = null
    set(value) {

        field = value
    }

    fun UpdateUsers(users: MutableList<User>){

        var diffUtil:DiffUtil.DiffResult = DiffUtil.calculateDiff(UserDiffCallback(this.users , users))

        diffUtil.dispatchUpdatesTo(this)

        this.users = users

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {


        return Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.context) , R.layout.items , parent , false))
    }

    override fun getItemCount(): Int = users.size


    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder.bind(users.get(position))



    }






    //-------------------------------Holder



   inner class Holder(val itemsBinding: ItemsBinding) : RecyclerView.ViewHolder(itemsBinding.root) {



        fun bind(user:User){

            itemsBinding.user = user
            itemsBinding.executePendingBindings()


            itemsBinding.root.setOnClickListener {



                onclickItemListener?.OnClick(user)

            }



        }








    }










}