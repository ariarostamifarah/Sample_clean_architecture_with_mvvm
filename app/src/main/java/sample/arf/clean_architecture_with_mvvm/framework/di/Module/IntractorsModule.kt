package sample.arf.clean_architecture_with_mvvm.framework.di.Module

import sample.arf.core.data.repo.RepoCategory
import sample.arf.core.data.repo.RepoUser
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
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IntractorsModule {


   //USER--------------------------------------------------------
    @Singleton
    @Provides
    fun ProvideAddUser(repoUser: RepoUser) = AddUser(repoUser)

    @Singleton
    @Provides
    fun ProvideGetAllUser(repoUser: RepoUser) = GetAllUser(repoUser)

    @Singleton
    @Provides
    fun ProvideGetUserByCategoryId(repoUser: RepoUser) = GetUserByCategoryId(repoUser)

    @Singleton
    @Provides
    fun ProvideGetUserById(repoUser: RepoUser) = GetUserById(repoUser)

    @Singleton
    @Provides
    fun ProvideRemoveUser(repoUser: RepoUser) = RemoveUser(repoUser)

    @Singleton
    @Provides
    fun ProvideUpdateUser(repoUser: RepoUser) = UpdateUser(repoUser)


    //CATEGORY----------------------------------------------------------------

    @Singleton
    @Provides
    fun ProvideAddCategory(repoCategory: RepoCategory) = AddCategory(repoCategory)

    @Singleton
    @Provides
    fun ProvideGetAllCategory(repoCategory: RepoCategory) = GetAllCategory(repoCategory)

    @Singleton
    @Provides
    fun ProvideGetCategoryById(repoCategory: RepoCategory) = GetCategoryById(repoCategory)

    @Singleton
    @Provides
    fun ProvideGetCategoryByName(repoCategory: RepoCategory) = GetCategoryByName(repoCategory)

    @Singleton
    @Provides
    fun ProvideGetNUmberOfCategory(repoCategory: RepoCategory) = GetNUmberOfCategory(repoCategory)

    @Singleton
    @Provides
    fun ProvideRemoveCategory(repoCategory: RepoCategory) = RemoveCategory(repoCategory)

    @Singleton
    @Provides
    fun ProvideUpdateCategory(repoCategory: RepoCategory) = UpdateCategory(repoCategory)








}