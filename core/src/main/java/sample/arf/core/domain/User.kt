package sample.arf.core.domain

import java.io.Serializable

data class User( val id:Long  ,
                  val name:String ,
                  val email:String ,
                  val country:String ,
                  val data:String ,
                  val category_id:Int ) : Serializable {

    companion object{

        val EMPTY = User( id =0 ,name = "", email = "", country = "", data = "", category_id = 0)



    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (country != other.country) return false
        if (data != other.data) return false
        if (category_id != other.category_id) return false

        return true
    }




}