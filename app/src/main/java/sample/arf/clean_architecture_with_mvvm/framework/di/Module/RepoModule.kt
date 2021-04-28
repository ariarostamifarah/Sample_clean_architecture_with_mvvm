package sample.arf.clean_architecture_with_mvvm.framework.di.Module

import sample.arf.core.data.repo.RepoCategory
import sample.arf.core.data.repo.RepoUser
import sample.arf.clean_architecture_with_mvvm.framework.RoomCategoryDataSource
import sample.arf.clean_architecture_with_mvvm.framework.RoomUserDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {



    @Singleton
    @Provides
    fun ProvideUserRepo(roomUserDataSource: RoomUserDataSource) = RepoUser(roomUserDataSource)

    @Singleton
    @Provides
    fun ProvideCategoryRepo(roomCategoryDataSource: RoomCategoryDataSource) = RepoCategory(roomCategoryDataSource)





}