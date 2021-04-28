package sample.arf.clean_architecture_with_mvvm.framework.db

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import sample.arf.core.domain.User


@Entity(tableName = "tb_users" ,  foreignKeys = arrayOf(ForeignKey(
    entity = CategoryEntity::class  ,
    parentColumns = arrayOf("_id") ,
    childColumns = arrayOf("_category_id") ,
    onDelete = CASCADE))


)
data class UserEntitiy(

    @PrimaryKey(autoGenerate = true)
    private var _id: Long = 0 ,
    private var _name: String,
    private var _email: String,
    private var _country: String,
    private var _data: String,
    private var _category_id: Int


) : BaseObservable() {


    var id: Long
        @Bindable get() = _id
        set(value) {
            _id = value

            notifyPropertyChanged(BR.id)

        }


    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value

            notifyPropertyChanged(BR.name)

        }


    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value

            notifyPropertyChanged(BR.email)

        }


    var country: String
        @Bindable get() = _country
        set(value) {
            _country = value

            notifyPropertyChanged(BR.country)

        }



    var data: String
        @Bindable get() = _data
        set(value) {
            _data = value

            notifyPropertyChanged(BR.data)

        }


    var category_id: Int
        @Bindable get() = _category_id
        set(value) {
            _category_id = value

            notifyPropertyChanged(BR.category_id)
        }



    companion object{


         fun toUser(userEntitiy: UserEntitiy): User {


            return User(
                userEntitiy.id,
                userEntitiy.name,
                userEntitiy.email,
                userEntitiy.country,
                userEntitiy.data,
                userEntitiy.category_id
            )
        }

         fun toUserEntitiy(user:User):UserEntitiy{

            return UserEntitiy(
                user.id,
                user.name,
                user.email,
                user.country,
                user.data,
                user.category_id
            )


        }

    }




}