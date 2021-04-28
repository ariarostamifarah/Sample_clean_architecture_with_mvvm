package sample.arf.core.usecases.user

import sample.arf.core.data.repo.RepoUser
import sample.arf.core.domain.User
import javax.inject.Inject

class AddUser(private val repoUser: RepoUser) {


    suspend operator fun invoke(user: User) = repoUser.Add(user)



}