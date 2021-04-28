package sample.arf.clean_architecture_with_mvvm.framework.di.Module

import android.app.Application

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
import sample.arf.clean_architecture_with_mvvm.framework.Intractors
import sample.arf.clean_architecture_with_mvvm.framework.RoomCategoryDataSource
import sample.arf.clean_architecture_with_mvvm.framework.RoomUserDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FrameworkModule {

    @Singleton
    @Provides
    fun ProvideIntractors(addUser: AddUser,
                          getAllUser: GetAllUser,
                          getUserByCategoryId: GetUserByCategoryId,
                          getUserById: GetUserById,
                          removeUser: RemoveUser,
                          updateUser: UpdateUser,

                          addCategory: AddCategory,
                          getAllCategory: GetAllCategory,
                          getCategoryById: GetCategoryById,
                          getCategoryByName: GetCategoryByName,
                          getNUmberOfCategory: GetNUmberOfCategory,
                          removeCategory: RemoveCategory,
                          updateCategory: UpdateCategory
    ) = Intractors(addUser ,
        getAllUser ,
        getUserByCategoryId ,
        getUserById ,
        removeUser ,
        updateUser ,
        addCategory ,
        getAllCategory ,
        getCategoryById ,
        getCategoryByName ,
        getNUmberOfCategory ,
        removeCategory,
        updateCategory
    )


    @Singleton
    @Provides
    fun ProvideRoomUserDataSource(application: Application) = RoomUserDataSource(application.applicationContext)

    @Singleton
    @Provides
    fun ProvideRoomCategoryDataSource(application: Application) = RoomCategoryDataSource(application.applicationContext)


}