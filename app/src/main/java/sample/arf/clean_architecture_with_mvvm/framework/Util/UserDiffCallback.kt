package sample.arf.clean_architecture_with_mvvm.framework.Util

import androidx.recyclerview.widget.DiffUtil
import sample.arf.core.domain.User

class  UserDiffCallback(private val OldList:MutableList<User>, private val NewList:MutableList<User>) :
    DiffUtil.Callback() {




    override fun getOldListSize(): Int = OldList?.size

    override fun getNewListSize(): Int = NewList?.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  OldList[oldItemPosition].id.equals(NewList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  OldList[oldItemPosition].equals(NewList[newItemPosition])
    }


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}