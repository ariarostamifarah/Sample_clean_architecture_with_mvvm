package sample.arf.clean_architecture_with_mvvm.framework.db

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey
import sample.arf.core.domain.Category


@Entity(tableName = "tb_category")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    private var _id: Int = 0,
    private var _cate_name: String
) : BaseObservable() {


    var id: Int
        @Bindable get() = _id
        set(value) {

            _id = value

            notifyPropertyChanged(BR.id)
        }


    var cate_name: String
        @Bindable get() = _cate_name
        set(value) {

            _cate_name = value

            notifyPropertyChanged(BR.cate_name)
        }


}

fun CategoryEntity.toCategory(): Category = Category(id, cate_name)

fun Category.toCategoryEntitiy(): CategoryEntity = CategoryEntity(cate_id, cate_name)