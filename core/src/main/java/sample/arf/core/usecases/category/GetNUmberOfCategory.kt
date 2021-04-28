package sample.arf.core.usecases.category

import sample.arf.core.data.repo.RepoCategory
import javax.inject.Inject

class GetNUmberOfCategory(private val repoCategory: RepoCategory) {


    suspend operator fun invoke():Int = repoCategory.GetCount()


}