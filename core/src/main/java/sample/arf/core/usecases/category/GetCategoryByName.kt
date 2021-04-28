package sample.arf.core.usecases.category

import sample.arf.core.data.repo.RepoCategory
import sample.arf.core.domain.Category
import javax.inject.Inject

class GetCategoryByName(private val repoCategory:RepoCategory) {


    suspend operator fun invoke(name:String):Category = repoCategory.GetByName(name)

}