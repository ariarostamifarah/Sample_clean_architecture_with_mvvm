package sample.arf.clean_architecture_with_mvvm.framework


import sample.arf.core.usecases.category.AddCategory
import sample.arf.core.usecases.category.GetAllCategory
import sample.arf.core.usecases.category.GetCategoryById
import sample.arf.core.usecases.category.GetCategoryByName
import sample.arf.core.usecases.category.GetNUmberOfCategory
import sample.arf.core.usecases.category.RemoveCategory
import sample.arf.core.usecases.category.UpdateCategory
import sample.arf.core.usecases.user.AddUser
import sample.arf.core.usecases.user.GetAllUser
import sample.arf.core.usecases.user.GetUserByCategoryId
import sample.arf.core.usecases.user.GetUserById
import sample.arf.core.usecases.user.RemoveUser
import sample.arf.core.usecases.user.UpdateUser

data class  Intractors(


        val addUser: AddUser,
        val getAllUser: GetAllUser,
        val getUserByCategoryId: GetUserByCategoryId,
        val getUserById: GetUserById,
        val removeUser: RemoveUser,
        val updateUser: UpdateUser,

        val addCategory: AddCategory,
        val getAllCategory: GetAllCategory,
        val getCategoryById: GetCategoryById,
        val getCategoryByName: GetCategoryByName,
        val getNUmberOfCategory: GetNUmberOfCategory,
        val removeCategory: RemoveCategory,
        val updateCategory: UpdateCategory



    )
